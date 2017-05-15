package sample.remote.calculator;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/*
1 接收 需要进行的计算消息, 生成一个用于计算的actor,并把消息发送给它
2 接收计算的结果消息: 乘 除,并打印结果
 */
public class CreationActor extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof Op.MathOp) {//如果是操作
			ActorRef calculator = getContext().actorOf(//1 每條消息都生成一個 actorRef ??
					Props.create(CalculatorActor.class)); //2 兩個不同的系統,也根據類名來創建
			calculator.tell(message, getSelf());

		} else if (message instanceof Op.MultiplicationResult) {//如果是相乘的結果
			Op.MultiplicationResult result = (Op.MultiplicationResult) message;
			System.out.printf("Mul result: %d * %d = %d\n", result.getN1(),
					result.getN2(), result.getResult());
			getContext().stop(getSender()); // 停止actor,会不会耗费性能?

		} else if (message instanceof Op.DivisionResult) {
			Op.DivisionResult result = (Op.DivisionResult) message;
			System.out.printf("Div result: %.0f / %d = %.2f\n", result.getN1(),
					result.getN2(), result.getResult());
			getContext().stop(getSender());

		} else {
			unhandled(message);
		}
	}
}
