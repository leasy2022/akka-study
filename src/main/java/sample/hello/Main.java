package sample.hello;

/**
 * Created by wushang on 2017/5/15.
 */
public class Main {

	public static void main(String[] args) {
		akka.Main.main(new String[] { HelloWorld.class.getName() });
		System.out.println("main over--"); //并不会阻塞结束, 主线程会结束
	}
}
