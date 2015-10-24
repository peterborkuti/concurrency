package common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {
	public static final SimpleDateFormat format =
		new SimpleDateFormat("HH:mm:ss.SSS");

	/**
	 * Gets the current thread to sleep for at least *fix* milliseconds
	 * and an added random millisecond between 0 and *rand*
	 * 
	 * If InerruptedException happens during sleeping, it stops sleeping
	 * and sets interrupt flag again to true to give a chance to the caller
	 * functions to check it and stop
	 * 
	 * @param l milliseconds to sleep
	 * @param m added random milliseconds between 0 and rand
	 * 
	 */
	@Deprecated
	public static void sleep(int l, int m) {
		Random r = new Random();
		try {
			long sleep = l + (m <= 0 ? 0 : r.nextInt(m));

			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * 
	 * @param fix
	 * @param rand
	 * @param unit
	 */
	public static void sleep(int fix, int rand, TimeUnit unit) {
		Random r = new Random();

		try {
			unit.sleep(fix);
			if (rand > 0) {
				unit.sleep(r.nextInt(rand));
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Prints the time, thread name a message to the console
	 * @param s
	 */
	public static void log(String s) {
		String time = format.format(new Date());
		String thread = Thread.currentThread().getName();
		System.out.println(time + " - " + thread + " - " + s);
	}

	/**
	 * Writes the prefix and strings in sArr and sleeps between prints
	 * @param prefix
	 * @param sArr
	 * @param fix
	 * @param rand
	 */
	public static void writeAndSleep(String prefix, String[] sArr, int fix, int rand) {
		if (prefix == null) {
			prefix = "";
		}

		if (sArr == null || sArr.length == 0) {
			sArr = new String[1];
		}

		String separator = ("".equals(prefix)) ? "" : " - ";

		for (int i = 0; i < sArr.length; i++) {
			log(prefix + separator + sArr[i]);
			sleep(fix, rand);

			if (Thread.currentThread().isInterrupted()) {
				break;
			}
		}
	}

	/**
	 * Writes the prefix and strings in sArr and sleeps between prints
	 * @param prefix
	 * @param sArr
	 * @param fix
	 * @param rand
	 */
	public static void writeAndSleep(String[] sArr, int fix, int rand) {
		writeAndSleep("", sArr, fix, rand);
	}

	/**
	 * returns with System.nanoTime().
	 * It can be used to measure time, @see #stopTimer(long) stopSimer
	 * 
	 * <pre>
	 * long timer = Util.startTimer();
	 * ...
	 * ...
	 * Util.stopTimer(timer);
	 * </pre>
	 * 
	 * @return long system time in nanoseconds
	 */
	public static long startTimer() {
		return System.nanoTime();
	}

	/**
	 * Logs the time between timer and currend system time.
	 * Parameter must be in nanoseconds.
	 * @see #startTimer() startTimer
	 * 
	 * @param timer starting time in nanoseconds
	 * 
	 */
	public static void stopTimer(long timer) {
		log("Timer:" + (System.nanoTime() - timer));
	}

	/**
	 * Effectively close the executor so no new runnable is accepted and
	 * tries to terminate all the running thread for 2 seconds.
	 * 
	 * Threads must catch InterruptedException and check the interrupted
	 * flag and stop if it's thread was interrupted.
	 * 
	 * @see {@link https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html} ExecutorService
	 * 
	 * @param pool
	 * @param waitSeconds
	 */
	public static void shutdownAndAwaitTermination(ExecutorService pool) {
		shutdownAndAwaitTermination(pool, 2);
	}

	/**
	 * Effectively close the executor so no new runnable is accepted and
	 * tries to terminate all the running thread for waitSeconds seconds.
	 * 
	 * Threads must catch InterruptedException and check the interrupted
	 * flag and stop if it's thread was interrupted.
	 * 
	 * @see {@link https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html} ExecutorService
	 * 
	 * @param pool
	 * @param waitSeconds
	 */
	public static void shutdownAndAwaitTermination(ExecutorService pool, long waitSeconds) {
		log("shutdown starts");
		pool.shutdown(); // Disable new tasks from being submitted

		pool.shutdownNow(); // Cancel currently executing tasks

		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(waitSeconds, TimeUnit.SECONDS)) {
				pool.shutdownNow(); // Cancel currently executing tasks

				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(waitSeconds, TimeUnit.SECONDS)) {
					System.err.println("Pool did not terminate");
				}
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
		log("shutdown ended");
	}

}
