package hu.bp.conway.modules;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniverseTest {

	@Test
	public void testUniverseInt() {
		int n = 3;
		Universe u = new Universe(n, false);
		assertEquals(n, u.n);
	}

	@Test
	public void testUniverseStringArray() {
		String s[] = {"*--","-*-","--*"};
		Universe u = new Universe(s);
		assertEquals(3, u.n);
		String q[] = u.toString().split(Universe.OUT_LINE_SEPARATOR);
		assertEquals(s[0], q[0]);
		assertEquals(s[1], q[1]);
		assertEquals(s[2], q[2]);
	}

	@Test
	public void testUniverseString() {
		String s = "*--\n-*-\n--*";
		Universe u = new Universe(s);
		assertEquals(3, u.n);
		String q = u.toString();
		assertEquals(s, q);
	}

	@Test
	public void testSet() {
		String s = "   ,   ,   ";
		Universe u = new Universe(s);
		assertFalse(u.isAlive(1, 2));
		u.set(1, 2, true);
		assertTrue(u.isAlive(1, 2));
	}

	@Test
	public void testSetUniverse() {
		Universe test;
		Universe in = new Universe(5, false);
		Universe stamp = new Universe(5, true);
		in.set(0, 0, stamp);
		assertEquals(in, stamp);

		in = new Universe(5, false);
		stamp = Stamps.getVerticalBlinker();
		in.set(0, 0, stamp);
		assertEquals(in, stamp);

		in = new Universe(3, false);
		stamp = new Universe("**,**");
		test = new Universe("** ,** ,   ");
		in.set(0, 0, stamp);
		assertEquals(in, test);

		in = new Universe(3, false);
		stamp = new Universe("**,**");
		test = new Universe(" **, **,   ");
		in.set(0, 1, stamp);
		assertEquals(in, test);

		in = new Universe(3, false);
		stamp = new Universe("**,**");
		test = new Universe("   , **, **");
		in.set(1, 1, stamp);
		assertEquals(in, test);

		in = new Universe(3, false);
		stamp = new Universe("**,**");
		test = new Universe("   ,  *,  *");
		in.set(1, 2, stamp);
		assertEquals(in, test);

		in = new Universe(4, false);
		stamp = new Universe("**,**");
		test = new Universe("**  ,**  ,  **,  **");
		in.set(0, 0, stamp);
		in.set(2, 2, stamp);
		assertEquals(in, test);
	}

	@Test
	public void testIsAlive() {
		String s[] = {"*  "," * ","  *"};
		Universe u = new Universe(s);

		for (int r = 0; r < s.length; r++) {
			for (int c = 0; c < s[r].length(); c++) {
				assertEquals(
					"*".equals(s[r].substring(c, c + 1)), u.isAlive(r, c));
			}
		}

	}

	@Test
	public void testFillString() {
		Universe u = new Universe(3, false);
		String s = "*--\n-*-\n--*";
		u.fill(s);
		String q = u.toString();
		assertEquals(s, q);
	}

	@Test
	public void testFillStringArray() {
		Universe u = new Universe(3, false);
		String s[] = {"*--","-*-","--*"};
		u.fill(s);
		String q[] = u.toString().split(Universe.OUT_LINE_SEPARATOR);
		assertEquals(s[0], q[0]);
		assertEquals(s[1], q[1]);
		assertEquals(s[2], q[2]);
	}

	@Test
	public void testToString() {
	}

}
