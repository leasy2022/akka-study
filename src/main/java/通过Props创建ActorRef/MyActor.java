package 通过Props创建ActorRef;

import akka.actor.UntypedActor;

/**
 * Created by wushang on 2017/4/12.
 */
public class MyActor extends UntypedActor{

	private int x;
	private int y;
//1 含成员变量,注意构造函数
	public MyActor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void preStart() throws Exception {
		System.out.println("myactor prestart");
		super.preStart();
	}

	@Override
	public void onReceive(Object message) {
		unhandled(message);
	}
}
