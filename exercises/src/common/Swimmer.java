package common;

import java.util.concurrent.Semaphore;

public class Swimmer implements Runnable {

	private final Semaphore semaphore;
	private final String name;

	public final String[] waiting = {
		"going to the pool",
		"trying to get permission for an empty track"
	};

	public final String[] swimming = {
		"got permission, starting to swim",
		"I'm tired, giving up, going to the sauna"
	};

	public Swimmer(String name, Semaphore semaphore) {
		this.semaphore = semaphore;
		this.name = name;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Util.writeAndSleep(name, waiting, 2000, 1000);
			if (!Thread.currentThread().isInterrupted()) {
				try {
					semaphore.acquire();
					Util.writeAndSleep(name, swimming, 2000, 1000);
					semaphore.release();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}
