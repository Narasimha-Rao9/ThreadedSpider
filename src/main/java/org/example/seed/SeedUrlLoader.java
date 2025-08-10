package org.example.seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SeedUrlLoader {
    private final List<String> seedUrls;
    private final long politenessDelayMillis;

    public SeedUrlLoader() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IllegalStateException("application.properties not found in resources");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }

        // Load and parse seed URLs
        String urls = props.getProperty("crawler.seed.urls");
        if (urls == null || urls.isBlank()) {
            throw new IllegalArgumentException("crawler.seed.urls must be set in application.properties");
        }
        this.seedUrls = Arrays.stream(urls.split(","))
                .map(String::trim)
                .filter(u -> !u.isEmpty())
                .toList();

        // Load politeness delay (default 2000 ms if not set)
        String delay = props.getProperty("crawler.politeness.delay.ms", "2000");
        this.politenessDelayMillis = Long.parseLong(delay);
    }

    public List<String> getSeedUrls() {
        return seedUrls;
    }

    public long getPolitenessDelay() {
        return politenessDelayMillis;
    }
}
