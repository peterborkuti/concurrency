package hu.bp.conway.modules;

public class Walker implements Runnable {

	public final Universe out;
	public final Universe in;
	public final int row, col, n; 

	public Walker(Universe in, Universe out, int topLeftRow, int topLeftCol, int n) {
		this.out = out;
		this.in = in;
		row = topLeftRow;
		col = topLeftCol;
		this.n = n;
	}

	@Override
	public void run() {
		for (int r = row; r < row + n; r++) {
			for (int c = col; c < col + n; c++) {
				if ((r < in.n) && (c < in.n) && (r >= 0) && (c >= 0)) {
					out.set(r, c, Rules.apply(in, r, c));
				}
			}
		}
	}


}
