package statistic;

import java.time.Instant;

public class Event {
    private final String name;
    private final Instant time;

    public Event(String name, Instant time) {
        this.name = name;
        this.time = time;
    }

    public Instant getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
