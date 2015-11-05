package interruption;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import common.Util;

public class Sleep {

	public static void Sleep3Secs() throws InterruptedException {
		long endTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3);
		Random r = new Random();

		while (System.currentTimeMillis() < endTime) {
			TimeUnit.MILLISECONDS.sleep(200);
			Double rnd = r.nextDouble();
			if (rnd < 0.1) {
				String message = "Something bad happened";
				Util.log(message);
				throw new RuntimeException("Something bad happened");
			};
			rnd = r.nextDouble();
			if (rnd < 0.1) {
				String message = "Ask for interrupt";
				Util.log(message);
				Thread.currentThread().interrupt();
			};
		}
	}
}
