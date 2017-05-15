package sample.hello;

/**
 * Created by wushang on 2017/5/15.
 */

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class Main2 {

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("Hello");
		ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
		system.actorOf(Props.create(Terminator.class, a), "terminator");
	}

	public static class Terminator extends UntypedActor {

		private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
		private final ActorRef ref;

		public Terminator(ActorRef ref) {
			this.ref = ref;
			getContext().watch(ref);//监控 ref
		}

		@Override
		public void onReceive(Object msg) {
			if (msg instanceof Terminated) { //如果收到 ref的Terminated 消息
				log.info("{} has terminated, shutting down system", ref.path());
				getContext().system().terminate();
			} else {
				unhandled(msg);
			}
		}

	}
}
