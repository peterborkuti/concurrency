/**
 * 
 */
package hu.bp.concurrency.binarylatch.resourceinitialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * There are some passengers and one gate-opener.
 * Passengers want to walk through the gate, however
 * the gate is closed.
 * 
 * GateOpener should find his keys, go to the gate and open it.
 * Passengers must be woken up, than they go to the gate, wait for being it open,
 * and get through the gate, if it is open.
 * 
 * GateOpener and passengers are different threads.
 * Gate opening is a latch (CountDownLatch with initial value 1).
 * 
 * @author Borkuti Peter
 *
 */
class Util {
	public static void sleep(int fix, int rand) {
		Random r = new Random();
		try {
			Thread.sleep(fix + r.nextInt(rand));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
class GateOpener implements Runnable {
	private final CountDownLatch gate;

	public GateOpener(CountDownLatch gate) {
		this.gate = gate;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " GateOpener - searching for the key.");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() + " GateOpener - going to the gate.");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() + " GateOpener - put the key into the hole");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() + " GateOpener - unlocking");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() + " GateOpener - opening the door");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() + " GateOpener - Pfhhh, gate is open now");

		gate.countDown();
	}

}

class Passenger implements Runnable {

	private final CountDownLatch gate;
	private final String name;

	public Passenger(CountDownLatch gate, String name) {
		this.gate = gate;
		this.name = name;
	} 

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " " + name + " - is woken up.");

		Util.sleep(1000, 1000);

		System.out.println(Thread.currentThread().getName() +  " " + name + " - going to gate.");

		Util.sleep(3000, 7000);

		System.out.println(Thread.currentThread().getName() +  " " + name + " - at the gate.");

		try {
			gate.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		System.out.println(Thread.currentThread().getName() +  " " + name + " - passed the gate.");
	}

}

public class Main {

	final static CountDownLatch binaryLatch = new CountDownLatch(1);

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(8);

		GateOpener ini = new GateOpener(binaryLatch);

		FutureTask<?> fIni = new FutureTask<Object>(ini, null);

		Passenger[] passengers = {
				new Passenger(binaryLatch, "Reni"),
				new Passenger(binaryLatch, "Vili"),
				new Passenger(binaryLatch, "Dani"),
				new Passenger(binaryLatch, "Pisti")};

		List<FutureTask<?>> f = new ArrayList<FutureTask<?>>();

		for (Passenger p: passengers) {
			f.add(new FutureTask<Object>(p, null));
		}

		System.out.println("Start threads");

		executor.execute(fIni);

		for (FutureTask<?> ff: f) {
			executor.execute(ff);
		}

		System.out.println("Waiting for passengers to get through the gate");
		for (FutureTask<?> ff: f) {
			ff.get();
		}

		System.out.println("Done");
	}

}
