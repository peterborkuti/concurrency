package timeunit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import common.Util;

public class ExecuteCodeAtDate {

	public static void main(String[] args) throws InterruptedException, ParseException {
		Util.log("app starts");

		ScheduledExecutorService executor =
				Executors.newScheduledThreadPool(1);

		IAmRunning runMe = new IAmRunning("Joe");

		Date targetDate =
			new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").
			parse("26-10-2015 08:52:00");

		long nowMillis = System.currentTimeMillis();
		long delay = targetDate.getTime() - nowMillis;

		executor.schedule(runMe, delay, TimeUnit.MILLISECONDS);

		Util.log("IAmRunning scheduled, main is sleeping");
		TimeUnit.MINUTES.sleep(2);

		Util.shutdownAndAwaitTermination(executor);
	}
}
