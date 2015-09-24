package hu.bp.conway.modules;

import java.util.Arrays;
import java.util.Random;


public class Universe {

	public static final String[] IN_LINE_SEPARATORS = {",", "\n"};
	public static final String OUT_LINE_SEPARATOR = "\n";
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

	public Universe(int n, float percent) {
		this.n = n;
		universe = new Liveness[n][n];
		fill(percent);
	}

	public Universe(String[] lines) {
		this(lines.length);
		fill(lines);
	}

	public Universe(String s) {
		this(s.split((
			s.contains(IN_LINE_SEPARATORS[0]) ?
				IN_LINE_SEPARATORS[0]:IN_LINE_SEPARATORS[1])));
	}


	public Universe(Universe in) {
		this.n = in.n;
		universe = new Liveness[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				universe[r][c] = in.universe[r][c];
			}
		}
	}

	public void set(int r, int c, boolean live) {
		universe[r][c] = Liveness.get(live);
	}

	public void set(int row, int col, Universe u) {
		for (int r = row; r < Math.min(n, row + u.n); r++) {
			for (int c = col; c < Math.min(n, col + u.n); c++) {
				set(r, c, u.isAlive(r - row, c - col));
			}
		}
	}

	public boolean isAlive(int r, int c) {
		if (r < 0 || c < 0 || r >= n || c >= n) {
			return false;
		}

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

	public void fill(float percent) {
		Random rnd = new Random();

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				universe[r][c] = Liveness.get(rnd.nextFloat() < percent);
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
				s.append(OUT_LINE_SEPARATOR);
			}
		}

		return s.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + Arrays.hashCode(universe);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universe other = (Universe) obj;
		if (n != other.n)
			return false;
		if (!Arrays.deepEquals(universe, other.universe))
			return false;
		return true;
	}

}
