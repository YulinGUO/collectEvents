package models;


import com.fasterxml.jackson.databind.JsonNode;
import helper.queue.QueueManager;


/**
 * Created by yulinguo on 1/23/15.
 * Model RunLog
 */
public class RunLog {

    public static String LOG = "log";

    public static void create(JsonNode js) {

        if (js.has(LOG)) {
            String logBody = js.findPath(LOG).asText();
            //add one log into queue
            QueueManager.INSTANCE.addRunLog(logBody);
        }

    }

}
