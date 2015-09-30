package semaphor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import common.Swimmer;
import common.Util;

public class SwimminPool {
	static final int LANE = 3;
	static final int SWIMMERS = 10;

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(LANE, true);

		ExecutorService executor = Executors.newFixedThreadPool(SWIMMERS);

		Util.log("6 o'clock, swimming pool opens");

		for (int i = 1; i <= SWIMMERS; i++) {
			executor.submit(new Swimmer("Swimmer-" + i, semaphore));
		}

		Util.sleep(20000, 1000);

		Util.log("22 o'clock, swimming pool closes");

		Util.shutdownAndAwaitTermination(executor, 5);
	}

}
