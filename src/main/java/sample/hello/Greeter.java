package sample.hello;

/**
 * Created by wushang on 2017/5/15.
 */
import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

	public static enum Msg {
		GREET, DONE;
	}

	@Override
	public void onReceive(Object msg) {
		if (msg == Msg.GREET) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Hello World!");//任务
			getSender().tell(Msg.DONE, getSelf());
		} else
			unhandled(msg);
	}

}
