package helper.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by yulinguo on 1/22/15.
 * Manage the queue which store all the msgs
 */
public enum QueueManager {
    INSTANCE;

    private ConcurrentLinkedQueue<String> runLogs;

    private QueueManager() {
        runLogs = new ConcurrentLinkedQueue<String>();
    }

    public void addRunLog(String log) {
        runLogs.add(log);
    }

    public ConcurrentLinkedQueue<String> getRunLogs() {
        return this.runLogs;
    }


}
