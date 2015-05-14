
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

	private int nbTC;
	private StringBuilder result = new StringBuilder();

	private static class EndException extends RuntimeException {
	}

	public void run() throws IOException {
		//        Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		nbTC = readInt(reader);
//		nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
			for (int tc = 1; tc <= nbTC; ++tc) {
				result.append("Case " + tc + ": ");
				result.append(oneTestCase(reader));
				result.append('\n');
			}
		} catch (EndException e) {
		}
		System.out.print(result);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.run();
	}

	private static class Grid {

		int r;
		int c;
		boolean[][] grid;

		public Grid(int r, int c) {
			this.r = r;
			this.c = c;
			this.grid = new boolean[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					grid[i][j] = true;
				}
			}
		}

		public boolean canMove(int x, int y) {
			return x >= 0 && y >= 0 && x < r && y < c && grid[x][y];
		}

		public void clear(int x, int y) {
			grid[x][y] = false;
		}

	}

	private ArrayList<Integer> createPos(int a, int b) {
		ArrayList<Integer> result = new ArrayList<>(2);
		result.add(a);
		result.add(b);
		return result;
	}

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();

		int r = readInt(reader);
		int c = readInt(reader);
		int m = readInt(reader);
		int n = readInt(reader);
		int w = readInt(reader);

		Grid grid = new Grid(r, c);
		for (int i = 0; i < w; i++) {
			int a = readInt(reader);
			int b = readInt(reader);
			grid.clear(a, b);
		}
		ArrayList<Integer> dx = new ArrayList<>();
		ArrayList<Integer> dy = new ArrayList<>();
		int tempm = m;
		m = Math.min(m,n);
		n = Math.max(tempm, n);
		if (m==0) {
			int[] dxa ={+m, +m, +n, -n }; 
			for (int d: dxa) {
				dx.add(d);
			}
			int[] dya ={+n, -n, +m, +m }; 
			for (int d: dya) {
				dy.add(d);
			}
			
		} else if (m != n) {
			int[] dxa ={+m, +m, -m, -m, +n, +n, -n, -n }; 
			for (int d: dxa) {
				dx.add(d);
			}
			int[] dya ={+n, -n, +n, -n, +m, -m, +m, -m }; 
			for (int d: dya) {
				dy.add(d);
			}
		} else {
			int[] dxa ={+m, +m, -m, -m}; 
			for (int d: dxa) {
				dx.add(d);
			}
			int[] dya ={+n, -n, +n, -n}; 
			for (int d: dya) {
				dy.add(d);
			}
		}
		int[][] result = new int[r][c];
		for (int i = 0; i < r; i++) {
			Arrays.fill(result[i], 0);
		}

		ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
		queue.add(createPos(0, 0));
		while (!queue.isEmpty()) {
			ArrayList<Integer> curPos = queue.remove(queue.size() - 1);
			int nbMoves = 0;
			for (int d = 0; d < dx.size(); d++) {
				int newx = curPos.get(0) + dx.get(d);
				int newy = curPos.get(1) + dy.get(d);
				if (grid.canMove(newx, newy)) {
					nbMoves++;
					if (result[newx][newy] == 0) {
						queue.add(createPos(newx, newy));
					}
				}
			}
			result[curPos.get(0)][curPos.get(1)] = nbMoves;
		}
		int nbOdd = 0;
		int nbEven = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (result[i][j] != 0 || (i+j == 0)) {
					if (result[i][j] % 2 == 0) {
						nbEven++;
					} else {
						nbOdd++;
					}
				}
			}
		}
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
		formatter.format("%d %d", nbEven, nbOdd);
		output.append(formatter.out());
		return output;
	}

	private int readInt(BufferedReader reader) throws IOException {
		int r = 0;
		boolean positive = true;
		char currentChar = (char) reader.read();

		while ((currentChar == ' ') || (currentChar == '\n')) {
			currentChar = (char) reader.read();
		}
		if (currentChar == (char) -1) {
			throw new IOException("end of stream");
		}
		if (currentChar == '-') {
			positive = false;
			currentChar = (char) reader.read();
		}
		while ((currentChar >= '0') && (currentChar <= '9')) {
			r = r * 10 + currentChar - '0';
			currentChar = (char) reader.read();
		}
		if (positive) {
			return r;
		} else {
			return -r;
		}
	}

	private long readLong(BufferedReader reader) throws IOException {
		long r = 0;
		boolean positive = true;
		char currentChar = (char) reader.read();

		while ((currentChar == ' ') || (currentChar == '\n')) {
			currentChar = (char) reader.read();
		}
		if (currentChar == (char) -1) {
			throw new IOException("end of stream");
		}
		if (currentChar == '-') {
			positive = false;
			currentChar = (char) reader.read();
		}
		while ((currentChar >= '0') && (currentChar <= '9')) {
			r = r * 10 + currentChar - '0';
			currentChar = (char) reader.read();
		}
		if (positive) {
			return r;
		} else {
			return -r;
		}
	}

	private char readChar(BufferedReader reader) throws IOException {
		return (char) reader.read();
	}
}
