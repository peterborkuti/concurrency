package hu.bp.conway.modules;

public class Coordinator implements Runnable {
	private Universe in, out;
	private int n;

	public Coordinator(int n) {
		n = 9;
		this.n = n;
		in = new Universe(
				"         ,    *    ,    *    ,    *    ," +
				"    *    ,    *    ,    *    ,    *    ,         ");
		print(in);
		out = new Universe(n, false);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			for (int r = 1; r < n - 2; r++) {
				for (int c = 1; c < n - 2; c++) {
					out.set(r, c, Rules.apply(in, r, c));
				}
			}
		}

		print(out);

		Universe tmp;
		tmp = in;
		in = out;
		out = tmp;
	}

	private void print(Universe u) {
		System.out.println(u.toString().replaceAll(",", "\n"));
	}

}
