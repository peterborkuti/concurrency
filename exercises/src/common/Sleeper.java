package common;

import common.Util;

public class Sleeper implements Runnable {

	private final static String[] messages = {
		"Let's wake up!",
		"Let's go to bed",
		"Sleeping"
	};

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Util.writeAndSleep(messages, 1000, 2000);
		}
	}
	
}