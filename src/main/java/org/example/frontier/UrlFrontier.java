package org.example.frontier;

import org.example.util.UrlUtils;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class UrlFrontier {
    private final PriorityBlockingQueue<FrontierEntry> globalQueue = new PriorityBlockingQueue<>();
    private final Map<String, Queue<FrontierEntry>> domainQueues = new ConcurrentHashMap<>();
    private final PolitenessManager politenessManager;

    public UrlFrontier(PolitenessManager politenessManager) {
        this.politenessManager = politenessManager;
    }

    public void addUrl(String url, int priority) {
        String domain = UrlUtils.extractDomain(url);
        long nextFetch = politenessManager.getNextAllowedFetchTime(domain);
        FrontierEntry entry = new FrontierEntry(url, priority, nextFetch);

        domainQueues.computeIfAbsent(domain, d -> new ConcurrentLinkedQueue<>()).add(entry);
        scheduleFromDomainQueue(domain);
    }

    private void scheduleFromDomainQueue(String domain) {
        Queue<FrontierEntry> domainQueue = domainQueues.get(domain);
        if (domainQueue != null && !domainQueue.isEmpty()) {
            FrontierEntry head = domainQueue.peek();
            if (System.currentTimeMillis() >= head.getNextFetchTime()) {
                globalQueue.offer(domainQueue.poll());
                politenessManager.updateNextFetchTime(domain);
            }
        }
    }

    public FrontierEntry getNextUrl() throws InterruptedException {
        return globalQueue.take(); // blocking call
    }
}
