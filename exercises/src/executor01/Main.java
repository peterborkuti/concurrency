package executor01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Sleeper;
import common.Util;


public class Main {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(8);

		Sleeper sleeper = new Sleeper();

		executor.execute(sleeper);

		Util.sleep(3000, 1000);

		Util.shutdownAndAwaitTermination(executor);
	}

}
