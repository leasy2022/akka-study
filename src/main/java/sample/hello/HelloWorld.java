package sample.hello;

/**
 * Created by wushang on 2017/5/15.
 */
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class HelloWorld extends UntypedActor {

	@Override
	public void preStart() {
		// create the greeter actor(在自己启动前,构架自己子的actor?)
		final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		// tell it to perform the greeting
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Greeter.Msg.DONE) {
			// when the greeter is done, stop this actor and with it the application
			getContext().stop(getSelf()); //当子的actor完成任务后, 父actor停止自己
		} else
			unhandled(msg);
	}
}
