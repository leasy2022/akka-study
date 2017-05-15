package 通过Props创建ActorRef;

import akka.japi.Creator;

/**
 * Created by wushang on 2017/4/12.
 */
public class MyActorCreator implements Creator<MyActor> {
	private int x;
	private int y;

	public MyActorCreator(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public MyActor create() throws Exception {
		return new MyActor(x, y);
	}
}
