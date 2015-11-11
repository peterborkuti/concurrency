package interruption;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import common.Util;

public class Sleeper03 implements Runnable{
	String [] messages = {
		"Start sleeping", "I have slept enough", "Somebody interrupted me",
		"Let's get out from the bed!"
	};

	Object stopper = new Object();

	@Override
	public void run() {
		Util.log(messages[0]);
		try {
			synchronized (stopper) {
				stopper.wait(TimeUnit.SECONDS.toMillis(30));
			}
		} catch (InterruptedException e) {
			Util.log(messages[2]);
		}
		Util.log(messages[3]);
	}

}
