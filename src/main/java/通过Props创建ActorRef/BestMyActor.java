package 通过Props创建ActorRef;

import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import scala.Option;

/**
 * Created by wushang on 2017/4/12.
 */
public class BestMyActor extends UntypedActor {

	private int x;
	private int y;

	public BestMyActor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 最佳实践就是 使用匿名类的方式+一个工厂函数
	public static Props props(int x, int y) {

		return Props.create(new Creator<BestMyActor>() {
			@Override
			public BestMyActor create() throws Exception {
				return new BestMyActor(x, y);
			}
		});
	}


	@Override
	public void preStart() throws Exception {
		System.out.println("best my actor --");
	}

	@Override
	public void onReceive(Object message)  {

	}
}
