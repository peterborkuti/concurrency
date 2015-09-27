package hu.bp.conway.modules;

import static org.junit.Assert.*;

import org.junit.Test;

public class WalkerTest {

	@Test
	public void testWalker() {
		Universe in = new Universe(4, 0.2f);
		Universe out = new Universe(5, 0.2f);
		Walker w = new Walker(in, out, 1, 2, 3, 4);
		assertEquals(in,  w.in);
		assertEquals(out,  w.out);
		assertEquals(1,  w.row);
		assertEquals(2,  w.col);
		assertEquals(3,  w.rows);
		assertEquals(4,  w.cols);
	}

	@Test
	public void testRun() {
		Universe in = Stamps.getVerticalBlinker();
		Universe out = new Universe(in.n, false);
		Walker w = new Walker(in, out, 0, 0, in.n, in.n);
		w.run();
		assertEquals(Stamps.getHorizontalBlinker().toString(), out.toString());

		in = Stamps.getHorizontalBlinker();
		out = new Universe(in.n, false);
		w = new Walker(in, out, 0, 0, in.n, in.n);
		w.run();
		assertEquals(Stamps.getVerticalBlinker().toString(), out.toString());

	}

}
