package statistic;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class EventStatisticImpl implements EventStatistic {
    private static final double MINUTES_PER_HOUR = Duration.ofHours(1).toMinutes();
    private static final double MILLIS_IN_HOUR = Duration.ofHours(1).toMillis();
    private final Clock clock;
    private final Queue<Event> eventQueue = new ArrayDeque<>();
    private final Map<String, Integer> eventCount = new HashMap<>();

    public EventStatisticImpl(Clock clock) {
        this.clock = clock;
    }

    private void deleteOldEventWithTime(long now) {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.peek();
            if (event.getTime().toEpochMilli() + MILLIS_IN_HOUR > now) {
                break;
            }
            eventQueue.remove();
            Integer count = eventCount.get(event.getName());
            if (count == 1) {
                eventCount.remove(event.getName());
            } else {
                eventCount.put(event.getName(), count - 1);
            }
        }
    }

    private void deleteOldEvent() {
        long now = clock.instant().toEpochMilli();
        deleteOldEventWithTime(now);
    }

    private double getRpm(Integer num) {
        if (num == null) {
            return 0;
        } else {
            return num / MINUTES_PER_HOUR;
        }
    }

    @Override
    public void incEvent(String name) {
        Instant now = clock.instant();
        eventQueue.add(new Event(name, now));
        eventCount.putIfAbsent(name, 0);
        eventCount.put(name, eventCount.get(name) + 1);
    }

    @Override
    public double getEventStatisticByName(String name) {
        deleteOldEvent();
        return getRpm(eventCount.get(name));
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        deleteOldEvent();
        Map<String, Double> ans = new HashMap<>();
        eventCount.forEach((key, value) -> ans.put(key, getRpm(value)));
        return ans;
    }

    @Override
    public void printStatistic() {
        deleteOldEvent();
        eventCount.forEach((key, value) -> System.out.println(key + " -> " + getRpm(value)));
    }

}