package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import common.SleeperOnce;
import common.Util;

public class RepeatAtFixedRate {

	public static void main(String[] args) {
		ScheduledExecutorService executor =
			Executors.newScheduledThreadPool(5);

		Sleeper joe = new Sleeper("Joe", 7, TimeUnit.SECONDS);

		Util.log("ScheduledSleeper repeated in 5 seconds");
		executor.scheduleAtFixedRate(joe, 1, 5, TimeUnit.SECONDS);

		Util.log("Waiting 30 seconds");
		Util.sleep(30, 0, TimeUnit.SECONDS);

		Util.log("Shutdown");
		Util.shutdownAndAwaitTermination(executor);
	}

}
