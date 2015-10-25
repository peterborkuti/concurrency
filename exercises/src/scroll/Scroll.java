package scroll;

import java.security.InvalidParameterException;

/**
 * Scrolls a text in a given length. If length is greater than
 * the text's length, it fills the remainder characters with
 * a filler.
 * @author Peter Borkuti
 *
 */
class Scroll {
	private final StringBuilder buffer;

	public final int LENGTH;
	public final char[] FILLER = new char[1];

	/**
	 * Creates a Scroll object
	 * 
	 * @param length The length of the scrolling text + the filler characters
	 * @param filler the character which will be added to the text to be the
	 *  given length 
	 * @param text this text will be scrolled
	 */
	public Scroll(int length, char filler, String text) {
		if (length < 2 || text == null || text.length() == 0) {
			throw new InvalidParameterException();
		}

		LENGTH = length;
		FILLER[0] = filler;
		buffer = new StringBuilder(LENGTH);
		String manyFiller = new String(new char[LENGTH]).replace('\0', filler);
		text = text + manyFiller;
		buffer.append(text.substring(0, LENGTH));
	}

	/**
	 * Scrolls the text to the right with one character
	 * @return the scrolled text
	 */
	public String scrollToRight() {
		buffer.insert(0, buffer.substring(LENGTH - 1));
		buffer.setLength(LENGTH);

		return buffer.toString();
	}

	/**
	 * Scrolls the text to the right with one character
	 * @return the scrolled text
	 */
	public String scrollToLeft() {
		buffer.append(buffer.substring(0, 1));
		buffer.delete(0,  1);
		buffer.setLength(LENGTH);

		return buffer.toString();
	}

	@Override
	public String toString() {
		return buffer.toString();
	}
}