package interruption;

import java.util.concurrent.TimeUnit;

import common.Util;

public class Sleeper implements Runnable{
	String [] messages = {
		"Start sleeping", "I have slept enough", "Somebody interrupted me",
		"Let's get out from the bed!"
	};

	@Override
	public void run() {
		Util.log(messages[0]);
		try {
			TimeUnit.SECONDS.sleep(30);
			Util.log(messages[1]);
		} catch (InterruptedException e) {
			Util.log(messages[2]);
		}
		Util.log(messages[3]);
	}

}
