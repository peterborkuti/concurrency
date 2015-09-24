package hu.bp.conway.modules;

import junit.framework.TestCase;

public class CoordinatorTest extends TestCase {

	public void testCoordinator() {
		fail("Not yet implemented");
	}

	public void testOneStep() {
		Universe u = new Universe(10, false);
		Universe test = new Universe(10, false);
		Universe stampH = Stamps.getHorizontalBlinker();
		Universe stampV = Stamps.getVerticalBlinker();

		u.set(0, 0, stampH);
		u.set(0, 5, stampH);
		u.set(5, 5, stampH);
		u.set(5, 0, stampH);

		test.set(0, 0, stampV);
		test.set(0, 5, stampV);
		test.set(5, 5, stampV);
		test.set(5, 0, stampV);

		Coordinator c = new Coordinator(u, 1, 1);
		c.oneStep();
		assertEquals(c.out, test);

		c = new Coordinator(u, 2, 1);
		c.oneStep();
		assertEquals(c.out, test);

		c = new Coordinator(u, 3, 1);
		c.oneStep();
		assertEquals(c.out, test);

	}

}
