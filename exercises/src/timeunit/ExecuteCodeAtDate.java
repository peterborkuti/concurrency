package timeunit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import common.Util;

public class ExecuteCodeAtDate {

	public static void main(String[] args) throws InterruptedException, ParseException, ExecutionException {
		Util.log("app starts");

		ScheduledExecutorService executor =
				Executors.newScheduledThreadPool(1);

		IAmRunning runMe = new IAmRunning("Joe");

		Date targetDate =
			new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").
			parse("01-11-2015 09:04:30");

		long nowMillis = System.currentTimeMillis();
		long delay = targetDate.getTime() - nowMillis;

		ScheduledFuture<?> future = 
		executor.schedule(runMe, delay, TimeUnit.MILLISECONDS);

		Util.log("IAmRunning scheduled, main is blocked");
		future.get();
		Util.log("Future got");

		Util.shutdownAndAwaitTermination(executor);
	}
}
