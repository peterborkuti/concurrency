package executor02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Sleeper;
import common.Util;


public class Main01 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(8);

		for (int i = 0; i < 10; i++) {
			executor.execute(new Sleeper());
		}

		Util.sleep(3000, 1000);

		Util.shutdownAndAwaitTermination(executor);
	}

}
