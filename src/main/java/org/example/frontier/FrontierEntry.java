package org.example.frontier;

public class FrontierEntry implements Comparable<FrontierEntry> {
    private final String url;
    private final int priority; // lower = higher priority
    private final long nextFetchTime; // millis since epoch

    public FrontierEntry(String url, int priority, long nextFetchTime) {
        this.url = url;
        this.priority = priority;
        this.nextFetchTime = nextFetchTime;
    }

    public String getUrl() { return url; }
    public int getPriority() { return priority; }
    public long getNextFetchTime() { return nextFetchTime; }

    @Override
    public int compareTo(FrontierEntry other) {
        // Primary: priority, Secondary: earliest fetch time
        int priorityCompare = Integer.compare(this.priority, other.priority);
        if (priorityCompare != 0) return priorityCompare;
        return Long.compare(this.nextFetchTime, other.nextFetchTime);
    }
}