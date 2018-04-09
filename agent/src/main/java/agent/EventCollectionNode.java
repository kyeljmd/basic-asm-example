package agent;

import java.time.LocalDateTime;

public class EventCollectionNode {

    private final LocalDateTime timeOfCollection;

    private final String metricName;

    private final long metricValue;


    public EventCollectionNode(LocalDateTime timeOfCollection, String metricName, long metricValue) {
        this.timeOfCollection = timeOfCollection;
        this.metricName = metricName;
        this.metricValue = metricValue;
    }

    public LocalDateTime getTimeOfCollection() {
        return timeOfCollection;
    }

    public String getMetricName() {
        return metricName;
    }

    public long getMetricValue() {
        return metricValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventCollectionNode eventCollectionNode = (EventCollectionNode) o;

        if (metricValue != eventCollectionNode.metricValue) return false;
        if (!timeOfCollection.equals(eventCollectionNode.timeOfCollection)) return false;
        return metricName.equals(eventCollectionNode.metricName);
    }

    @Override
    public int hashCode() {
        int result = timeOfCollection.hashCode();
        result = 31 * result + metricName.hashCode();
        result = 31 * result + (int) (metricValue ^ (metricValue >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "" +
                "timeOfCollection=" + timeOfCollection +
                ", metricName='" + metricName + '\'' +
                ", metricValue=" + metricValue + "ns";
    }
}
