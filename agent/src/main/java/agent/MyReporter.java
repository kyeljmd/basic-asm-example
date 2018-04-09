package agent;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyReporter {

    private static final Map<Long, List<EventCollectionNode>> threadResponseTimes = new ConcurrentHashMap<>();

    /***
     *  Stores the response of time a given method in nanoseconds on an in-memory data structure
     *  Implementation is threadsafe
     * @param metricName  - Name of the method we are measuring the response time
     * @param totalNanoTime - Total response time of the method in nano seconds
     */
    public static void report(String metricName, long totalNanoTime) {
        long threadId = Thread.currentThread().getId();
        LocalDateTime timeOfCollection = LocalDateTime.now(ZoneOffset.UTC);
        EventCollectionNode eventEventCollectionNode = new EventCollectionNode(timeOfCollection,
                metricName, totalNanoTime);

        if (!threadResponseTimes.containsKey(threadId)) {
            threadResponseTimes.put(threadId, Collections.synchronizedList(new ArrayList<EventCollectionNode>()));
        }
        threadResponseTimes.get(threadId).add(eventEventCollectionNode);
    }

    /**
     * Method for reading out all the response times for each given thread that has been
     * Recorded in memory
     */
    public static void readEventThreads() {
        threadResponseTimes
                .entrySet()
                .forEach(entry -> {
                    System.out.println(">>> Events for Thread ID: " + entry.getKey());
                    List<EventCollectionNode> events = entry.getValue();
                    synchronized (events) {
                        events.forEach(e -> System.out.println(e));
                    }
        });
    }
}
