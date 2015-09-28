package executor02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Thinker01;
import common.Util;


public class Main02 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		executor.execute(new Thinker01());

		Util.sleep(3000, 1000);

		Util.shutdownAndAwaitTermination(executor);
	}

}
