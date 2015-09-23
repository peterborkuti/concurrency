package hu.bp.conway.modules;

import static org.junit.Assert.*;

import org.junit.Test;

public class RulesTest {

	@Test
	public void testLiveNeighbours() {
		Universe u = new Universe(3, false);
		assertEquals(0, Rules.liveNeighbours(u,1,1));
		u.fill(true);
		assertEquals(8, Rules.liveNeighbours(u,1,1));
		u = new Universe(" * ,* *, * ");
		assertEquals(4, Rules.liveNeighbours(u,1,1));
		u = new Universe("* *,   ,* *");
		assertEquals(4, Rules.liveNeighbours(u,1,1));
		u = new Universe("     ,  *  ,  *  ,  *  ,     ");
		assertEquals(3, Rules.liveNeighbours(u,2,1));
	}

	@Test
	public void testApply() {
		Universe u = new Universe(3, false);
		//Any live cell with fewer than two live neighbours dies,
		// as if caused by under-population.
		u.set(1, 1, true);
		assertFalse(Rules.apply(u,1,1));
		u.set(0, 0, true);
		assertFalse(Rules.apply(u,1,1));
		//Any live cell with two or three live neighbours
		// lives on to the next generation.
		u.set(2, 2, true);
		assertTrue(Rules.apply(u,1,1));
		u.set(0, 2, true);
		assertTrue(Rules.apply(u,1,1));
		//Any live cell with more than three live neighbours dies,
		// as if by overcrowding.
		u.set(0, 1, true);
		assertFalse(Rules.apply(u,1,1));
		//Any dead cell with exactly three live neighbours
		// becomes a live cell, as if by reproduction.
		u.set(1, 1, false);
		u.set(0, 1, false);
		assertTrue(Rules.apply(u,1,1));
	}

}
