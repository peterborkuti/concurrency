package common;

import common.Util;

public class SleeperOnce implements Runnable {

	public final static String[] messages = {
		"Let's wake up!",
		"Let's go to bed",
		"Sleeping"
	};

	@Override
	public void run() {
			Util.writeAndSleep(messages, 2000, 2000);
	}
	
}