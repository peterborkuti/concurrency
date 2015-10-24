package scheduler;

import java.util.concurrent.TimeUnit;

import common.Util;

public class Sleeper implements Runnable {

	public final static String[] messages = {
		"Let's wake up!",
		"Let's go to bed",
		"Sleeping"
	};

	public final String name;
	public final int delay;

	public Sleeper(String name, int livingPeriod, TimeUnit unit) {
		this.name = name;
		delay = (int)unit.toMillis(livingPeriod) / (messages.length);
	}

	@Override
	public void run() {
			Util.writeAndSleep(name, messages, delay, 0);
	}

}