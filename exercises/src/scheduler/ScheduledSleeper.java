package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import common.SleeperOnce;
import common.Util;

public class ScheduledSleeper {

	public static void main(String[] args) {
		ScheduledExecutorService scheduler =
			Executors.newScheduledThreadPool(1);

		Sleeper joe = new Sleeper("Joe", 5, TimeUnit.SECONDS);

		Util.log("ScheduledSleeper scheduled");
		scheduler.schedule(joe, 3, TimeUnit.SECONDS);

		Util.log("Waiting 30 seconds");
		Util.sleep(30, 0, TimeUnit.SECONDS);

		Util.log("Shutdown");
		Util.shutdownAndAwaitTermination(scheduler);
	}

}
