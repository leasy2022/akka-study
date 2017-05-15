package 通过Props创建ActorRef;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by wushang on 2017/4/12.
 */
public class 通过Creator创建 {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("system");
		ActorRef actorRef = system.actorOf(Props.create(new MyActorCreator(3,4)),"myactor");
	}
}
