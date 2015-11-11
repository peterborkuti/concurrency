package interruption;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import common.Util;

public class Sleeper02 implements Runnable{
	String [] messages = {
		"Start sleeping", "I have slept enough", "Somebody interrupted me",
		"Let's get out from the bed!"
	};

	BlockingQueue<Integer> bq = new ArrayBlockingQueue<Integer>(1);

	@Override
	public void run() {
		Util.log(messages[0]);
		try {
			bq.poll(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Util.log(messages[2]);
		}
		Util.log(messages[3]);
	}

}
