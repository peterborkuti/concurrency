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
				out.set(r, c, Rules.apply(in, r, c));
			}
		}
	}


}
