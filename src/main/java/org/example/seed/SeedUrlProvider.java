package org.example.seed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SeedUrlProvider {
    public List<String> loadSeeds() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("application.properties not found in resources folder");
            }
            properties.load(input);
        }

        String seedsStr = properties.getProperty("seed.urls", "");
        return Arrays.stream(seedsStr.split(","))
                .map(String::trim)
                .filter(url -> !url.isEmpty())
                .map(this::normalizeUrl)
                .collect(Collectors.toList());
    }

    private String normalizeUrl(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }
}
