package hu.bp.conway.modules;

public class Coordinator implements Runnable {
	private Universe in, out;

	public Coordinator(int n) {
		in = new Universe(
				"         ,    *    ,    *    ,    *    ," +
				"    *    ,    *    ,    *    ,    *    ,         ");
		print(in);
		out = new Universe(n, false);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Walker w = new Walker(in, out, 1, 1, in.n - 2);
			w.run();

			print(out);

			Universe tmp;
			tmp = in;
			in = out;
			out = tmp;
		}
	}

	private void print(Universe u) {
		System.out.println();
		System.out.println(u.toString().replaceAll(",", "\n").replaceAll(" ", "-"));
	}

}
