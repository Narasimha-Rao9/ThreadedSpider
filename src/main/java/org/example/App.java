package org.example;

import org.example.frontier.FrontierEntry;
import org.example.frontier.PolitenessManager;
import org.example.frontier.UrlFrontier;
import org.example.seed.SeedUrlLoader;
import org.example.seed.SeedUrlProvider;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        SeedUrlLoader seedLoader = new SeedUrlLoader();

        PolitenessManager politenessManager = new PolitenessManager(seedLoader.getPolitenessDelay());
        UrlFrontier frontier = new UrlFrontier(politenessManager);

        seedLoader.getSeedUrls().forEach(url -> frontier.addUrl(url, 0));

        Runnable crawlerWorker = () -> {
            try {
                while (true) {
                    FrontierEntry entry = frontier.getNextUrl();
                    System.out.printf("[%s] Fetching: %s (priority=%d)\n",
                            Thread.currentThread().getName(),
                            entry.getUrl(),
                            entry.getPriority()
                    );
                    Thread.sleep(500); // simulate fetch time
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start 3 consumer threads
        for (int i = 0; i < 3; i++) {
            new Thread(crawlerWorker, "Crawler-" + i).start();
        }
    }
}
