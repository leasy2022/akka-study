package sample.remote.calculator;

import static java.util.concurrent.TimeUnit.SECONDS;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorIdentity;
import akka.actor.Identify;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.actor.ReceiveTimeout;
import akka.japi.Procedure;

/*

 */
public class LookupActor extends UntypedActor {

	private final String path;
	private ActorRef calculator = null;

	public LookupActor(String path) {
		this.path = path;
		sendIdentifyRequest(); //構造函數中調用
	}

	private void sendIdentifyRequest() {
		getContext().actorSelection(path).tell(new Identify(path), getSelf());
		getContext()
				.system()
				.scheduler()
				.scheduleOnce(Duration.create(3, SECONDS), getSelf(),
						ReceiveTimeout.getInstance(), getContext().dispatcher(), getSelf());
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ActorIdentity) {
			calculator = ((ActorIdentity) message).getRef();
			if (calculator == null) {
				System.out.println("Remote actor not available: " + path);
			} else {
				getContext().watch(calculator);
				getContext().become(active, true);
			}

		} else if (message instanceof ReceiveTimeout) {
			sendIdentifyRequest();  //接收超時,重新發送

		} else {
			System.out.println("Not ready yet");

		}
	}

	Procedure<Object> active = new Procedure<Object>() {
		@Override
		public void apply(Object message) {
			if (message instanceof Op.MathOp) {
				// send message to server actor
				calculator.tell(message, getSelf());

			} else if (message instanceof Op.AddResult) {
				Op.AddResult result = (Op.AddResult) message;
				System.out.printf("Add result: %d + %d = %d\n", result.getN1(),
						result.getN2(), result.getResult());

			} else if (message instanceof Op.SubtractResult) {
				Op.SubtractResult result = (Op.SubtractResult) message;
				System.out.printf("Sub result: %d - %d = %d\n", result.getN1(),
						result.getN2(), result.getResult());

			} else if (message instanceof Terminated) {//因為前面  已經 watch 了
				System.out.println("Calculator terminated");
				sendIdentifyRequest(); //重新發起連接
				getContext().unbecome();

			} else if (message instanceof ReceiveTimeout) {
				// ignore

			} else {
				unhandled(message);
			}

		}
	};
}
