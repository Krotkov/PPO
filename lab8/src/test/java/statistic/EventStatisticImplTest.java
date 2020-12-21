package statistic;

import clock.FakeClock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class EventStatisticImplTest {
    private FakeClock clock;
    private EventStatistic eventsStatistic;
    private static final double DELTA = 1e-9;

    @Before
    public void before() {
        clock = new FakeClock(Instant.now());
        eventsStatistic = new EventStatisticImpl(clock);
    }

    private void checkEventStatistic(String name, double expected) {
        Assert.assertEquals(eventsStatistic.getEventStatisticByName(name), expected, DELTA);
    }

    private void checkAllEventStatistics(Map<String, Double> expected) {
        Map<String, Double> events = eventsStatistic.getAllEventStatistic();
        Assert.assertEquals(events.size(), expected.size());
        expected.forEach((name, res) -> Assert.assertEquals(events.get(name), res, DELTA));
    }

    private double rpm(int num) {
        return num * 1.0 / 60;
    }

    @Test
    public void emptyTest() {
        checkEventStatistic("event", rpm(0));
        checkAllEventStatistics(Map.of());
    }

    @Test
    public void simpleEventTest() {
        eventsStatistic.incEvent("event");
        eventsStatistic.incEvent("event");
        checkEventStatistic("event", rpm(2));
        checkAllEventStatistics(Map.of("event", rpm(2)));
    }

    @Test
    public void eventTest() {
        eventsStatistic.incEvent("event");
        clock.addTime(Duration.ofMinutes(60));
        checkEventStatistic("event", rpm(0));
        checkAllEventStatistics(Map.of());
    }


    @Test
    public void bigEventTest() {
        eventsStatistic.incEvent("event");
        checkEventStatistic("event", rpm(1));
        clock.addTime(Duration.ofMinutes(40));
        eventsStatistic.incEvent("event");
        checkEventStatistic("event", rpm(2));
        clock.addTime(Duration.ofMinutes(40));
        checkEventStatistic("event", rpm(1));
    }

    @Test
    public void allEventTest() {
        eventsStatistic.incEvent("event1");
        eventsStatistic.incEvent("event2");
        eventsStatistic.incEvent("event2");
        clock.addTime(Duration.ofMinutes(40));
        eventsStatistic.incEvent("event2");
        clock.addTime(Duration.ofMinutes(40));
        checkEventStatistic("event1", rpm(0));
        checkEventStatistic("event2", rpm(1));
        checkAllEventStatistics(Map.of("event2", rpm(1)));
    }

}