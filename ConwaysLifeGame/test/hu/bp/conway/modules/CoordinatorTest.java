package hu.bp.conway.modules;

import hu.bp.common.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import junit.framework.TestCase;

public class CoordinatorTest extends TestCase {

	@Test
	public void testOneStep() {
		Universe u = new Universe(10, false);
		Universe test = new Universe(10, false);
		Universe stampH = Stamps.getHorizontalBlinker();
		Universe stampV = Stamps.getVerticalBlinker();
		ExecutorService executor = Executors.newFixedThreadPool(3);

		u.set(0, 0, stampH);
		u.set(0, 5, stampH);
		u.set(5, 5, stampH);
		u.set(5, 0, stampH);

		test.set(0, 0, stampV);
		test.set(0, 5, stampV);
		test.set(5, 5, stampV);
		test.set(5, 0, stampV);

		Coordinator c = new Coordinator(u, executor, 1, 1);
		c.oneStep();
		assertEquals(c.out, test);

		c = new Coordinator(u, executor, 2, 1);
		c.oneStep();
		assertEquals(c.out, test);

		c = new Coordinator(u, executor, 3, 1);
		c.oneStep();
		assertEquals(c.out, test);

		Util.shutdownAndAwaitTermination(executor);

	}

}
