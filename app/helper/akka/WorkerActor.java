package helper.akka;

import akka.actor.UntypedActor;
import helper.rpc.sendMsg;

import java.util.List;

/**
 * Created by yulinguo on 1/22/15.
 */
public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DispatchMsg) {
            DispatchMsg msg = (DispatchMsg) message;
            String business = msg.business;
            List<String> datas = (List<String>) msg.data;

            sendMsg.ToFlume.sendDataToFlume(datas);

        }
    }
}
