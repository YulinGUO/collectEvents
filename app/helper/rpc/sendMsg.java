package helper.rpc;

import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.apache.flume.event.EventBuilder;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yulinguo on 1/26/15.
 */
public enum sendMsg {

    ToFlume("127.0.0.1",41414);

    private RpcClient client;
    private String hostname;
    private int port;


    sendMsg(String hostname, int port) {
        // Setup the RPC connection
        this.hostname = hostname;
        this.port = port;
        this.client = RpcClientFactory.getDefaultInstance(hostname, port);
    }

    public void sendDataToFlume(List<String> datas) {

        List<Event> es = new LinkedList<Event>();
        for (String data : datas){
            // Create a Flume Event object that encapsulates the sample data
            Event event = EventBuilder.withBody(data, Charset.forName("UTF-8"));
            es.add(event);
        }

        // Send the event
        try {
            client.appendBatch(es);
            System.out.println("data sent");
        } catch (EventDeliveryException e) {
            // clean up and recreate the client

            client.close();
            client = null;
            client = RpcClientFactory.getDefaultInstance(hostname, port);
        }
    }
    public void cleanUp() {
        // Close the RPC connection
        client.close();
    }
}
