package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import common.SleeperOnce;
import common.Util;

public class RepeatWithFixedDelay {

	public static void main(String[] args) {
		ScheduledExecutorService executor =
			Executors.newScheduledThreadPool(5);

		Sleeper joe = new Sleeper("Joe", 5, TimeUnit.SECONDS);

		Util.log("ScheduledSleeper at fixed delay 2 seconds");
		executor.scheduleWithFixedDelay(joe, 1, 2, TimeUnit.SECONDS);

		Util.log("Waiting 30 seconds");
		Util.sleep(30, 0, TimeUnit.SECONDS);

		Util.log("Shutdown");
		Util.shutdownAndAwaitTermination(executor);
	}

}
