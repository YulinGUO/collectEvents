package helper.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import helper.queue.QueueManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by yulinguo on 1/22/15.
 * Dispatcher is the master in akka actor
 */
public class Dispatcher extends UntypedActor {

    ActorRef workRouter = getContext().actorOf(
            new Props(WorkerActor.class).withRouter(new RoundRobinRouter(40))
            , "transferRouter");

    @Override
    public void onReceive(Object message) throws Exception {
        ConcurrentLinkedQueue<String> runLogs = QueueManager.INSTANCE.getRunLogs();
        dispatch(runLogs);

    }

    private void dispatch(Queue<?> queue) {

        if (!queue.isEmpty()) {
            Object obj = queue.poll();
            List<String> data = new ArrayList<>();
            while (obj != null) {
                data.add(obj.toString());
                obj = queue.poll();
            }

            workRouter.tell(new DispatchMsg("runlogs", data), getSelf());
        }
    }
}
