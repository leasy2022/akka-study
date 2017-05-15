import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by wushang on 2017/4/2.
 */
/*

 */
public class MyUntypedActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object message) {
		if (message instanceof String) {
			log.info("Receiving String Message: {}", message);
			getSender().tell(message, getSelf());

		} else {
			unhandled(message);
		}

	}

}
