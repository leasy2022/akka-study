package 通过Props创建ActorRef;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import 通过Props创建ActorRef.MyActor;

/**
 * Created by wushang on 2017/4/12.
 */
public class HelloWorld {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("myActorSystem");
		//Props.create() 实际调用的MyActor的构造函数
		ActorRef myActor = system.actorOf(Props.create(MyActor.class, 1,2), "myactor");
	}
}
