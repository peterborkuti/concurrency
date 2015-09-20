package hu.bp.conway.modules;

public class Universe {

	public static final String LINE_SEPARATOR = ",";
	private final Liveness[][] universe;
	public final int n;

	private Universe(int n) {
		this.n = n;
		universe = new Liveness[n][n];
	}

	public Universe(int n, boolean live) {
		this.n = n;
		universe = new Liveness[n][n];
		fill(live);
	}

	public Universe(String[] lines) {
		this(lines.length);
		fill(lines);
	}

	public Universe(String s) {
		this(s.split(LINE_SEPARATOR));
	}


	public void set(int r, int c, boolean live) {
		universe[r][c] = Liveness.get(live);
	}

	public boolean isAlive(int r, int c) {
		return universe[r][c] == Liveness.LIVE;
	}

	public void fill(String code) {
		String[] codes = code.split(",");
		fill(codes);
	}

	public void fill(String[] code) {
		for (int r = 0; r < Math.min(n, code.length); r++) {
			for (int c = 0; c < Math.min(n, code[r].length()); c++) {

				universe[r][c] = Liveness.get(code[r].substring(c, c + 1));

				}
		}
	}

	public void fill(boolean live) {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				universe[r][c] = Liveness.get(live);
			}
		}
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				s.append(universe[r][c].c);
			}
			if (r < n - 1) {
				s.append(LINE_SEPARATOR);
			}
		}

		return s.toString();
	}

}
