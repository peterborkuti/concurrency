package timeunit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import common.Util;

public class ExecuteCodeAtNextMinute {

	public static void main(String[] args) throws InterruptedException {
		Util.log("app starts");

		ScheduledExecutorService executor =
				Executors.newScheduledThreadPool(1);

		IAmRunning runMe = new IAmRunning("Joe");

		long nowMillis = System.currentTimeMillis();
		long nowMinute = TimeUnit.MILLISECONDS.toMinutes(nowMillis);
		long nextMinuteInMillis = TimeUnit.MINUTES.toMillis(nowMinute + 1);
		long delay = nextMinuteInMillis - nowMillis;

		executor.schedule(runMe, delay, TimeUnit.MILLISECONDS);

		Util.log("IAmRunning scheduled, main is sleeping");
		TimeUnit.SECONDS.sleep(61);

		Util.shutdownAndAwaitTermination(executor);
	}
}
