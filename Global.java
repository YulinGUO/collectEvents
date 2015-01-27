
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import helper.akka.Dispatcher;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

/*
 * GlobalSettings is instantiated by the framework when an application starts,
 * to let you perform specific tasks at start-up or shut-down.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application " + app.toString() + " has started");

        ActorRef dispatcher = Akka.system().actorOf(new Props(Dispatcher.class));

        Akka.system().scheduler().schedule(
                Duration.create(200,TimeUnit.MICROSECONDS),
                Duration.create(2,TimeUnit.SECONDS),
                dispatcher,
                "run",
                Akka.system().dispatcher(),
                null
        );

    }

    @Override
    public void onStop(Application app) {
        Akka.system().shutdown();
        super.onStop(app);
    }

}