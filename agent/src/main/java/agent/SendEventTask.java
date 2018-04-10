package agent;


public class SendEventTask implements Runnable {
    @Override
    public void run() {
        MyReporter.readEventThreads();
    }
}
