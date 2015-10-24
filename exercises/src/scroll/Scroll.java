package scroll;

class Scroll {
	private final StringBuilder buffer;

	public final int LENGTH;
	public final char[] FILLER = new char[1];

	public Scroll(int length, char filler, String s) {
		LENGTH = length;
		FILLER[0] = filler;
		buffer = new StringBuilder(LENGTH);
		String manyFiller = new String(new char[LENGTH]).replace('\0', filler);
		s = s + manyFiller;
		buffer.append(s.substring(0, LENGTH));
	}

	public String scrollToRight() {
		buffer.insert(0, buffer.substring(LENGTH - 1));
		buffer.setLength(LENGTH);

		return buffer.toString();
	}

	@Override
	public String toString() {
		return buffer.toString();
	}
}