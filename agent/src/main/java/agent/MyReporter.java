package agent;

/**
 * Created by kyel on 4/5/2018.
 */
public class MyReporter {

    public static void report(String metricName, long totalNanoTime) {
        System.out.println(metricName + " Ran in "+ totalNanoTime + " Nanoseconds");
    }
}
