package interruption;

import java.util.concurrent.TimeUnit;

public class Runner01 {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Printer01()).start();

		TimeUnit.SECONDS.sleep(30);
	}

}
