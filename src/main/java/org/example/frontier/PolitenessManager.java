package org.example.frontier;

import java.util.concurrent.ConcurrentHashMap;

public class PolitenessManager {
    private final ConcurrentHashMap<String, Long> domainNextFetch = new ConcurrentHashMap<>();
    private final long delayMillis;

    public PolitenessManager(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getNextAllowedFetchTime(String domain) {
        return domainNextFetch.getOrDefault(domain, 0L);
    }

    public void updateNextFetchTime(String domain) {
        domainNextFetch.put(domain, System.currentTimeMillis() + delayMillis);
    }
}
