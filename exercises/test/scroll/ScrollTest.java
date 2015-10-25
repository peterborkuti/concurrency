package scroll;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScrollTest {

	@Test
	public void testScroll() {
		Scroll scroll = new Scroll(3, ' ', "abcdef");
		assertEquals(scroll.toString(), "abc");

		scroll = new Scroll(3, 'c', "ab");

		assertEquals(scroll.LENGTH, 3);
		assertEquals(scroll.FILLER.length, 1);
		assertEquals(scroll.FILLER[0], 'c');
		assertEquals(scroll.toString(), "abc");
	}

	@Test
	public void testScrollToRight() {
		Scroll scroll = new Scroll(3, 'c', "ab");

		assertEquals(scroll.scrollToRight(), "cab");
		assertEquals(scroll.scrollToRight(), "bca");
		assertEquals(scroll.scrollToRight(), "abc");
		assertEquals(scroll.scrollToRight(), "cab");
	}

}
