package agent;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyReporter {

    private static final Map<Long, List<EventCollectionNode>> threadResponseTimes = new ConcurrentHashMap<>();

    public synchronized static void report(String metricName, long totalNanoTime) {
        long threadId = Thread.currentThread().getId();
        LocalDateTime timeOfCollection = LocalDateTime.now(ZoneOffset.UTC);
        EventCollectionNode eventEventCollectionNode = new EventCollectionNode(timeOfCollection,
                metricName, totalNanoTime);

        if (!threadResponseTimes.containsKey(threadId)) {
            threadResponseTimes.put(threadId, Collections.synchronizedList(new ArrayList<EventCollectionNode>()));
        }
        threadResponseTimes.get(threadId).add(eventEventCollectionNode);
    }

    /// Read the entry of all of the threadResponseTimes
    public static void readEventThreads() {
        Set<Map.Entry<Long, List<EventCollectionNode>>> entrySet = threadResponseTimes.entrySet();
        
        entrySet.forEach(entry -> {
            System.out.println(">>> Events for Thread ID: " + entry.getKey());
            List<EventCollectionNode> events = entry.getValue();
            synchronized (events) {
                events.forEach(e -> System.out.println(e));
            }
        });
//        for(Map.Entry<Long, List<EventCollectionNode>> entry: entrySet) {
//            System.out.println(">>> Events for Thread ID: "+entry.getKey());
//            List<EventCollectionNode> events = entry.getValue();
//            synchronized(events) {
//                for(EventCollectionNode event: events) {
//                    System.out.println(event);
//                }
//            }
//        }
    }
}
