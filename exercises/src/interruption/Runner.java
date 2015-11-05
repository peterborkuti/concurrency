package interruption;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.Util;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		executor.execute(new Printer01());

		TimeUnit.SECONDS.sleep(30);

		Util.shutdownAndAwaitTermination(executor);
	}

}
