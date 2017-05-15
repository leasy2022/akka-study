package 通过Props创建ActorRef;

import akka.actor.ActorSystem;

/**
 * Created by wushang on 2017/4/12.
 */
public class Best {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("system");
		system.actorOf(BestMyActor.props(3,4), "bestActor");
	}
}
