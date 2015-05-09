
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

	private int nbTC;
	private StringBuilder result = new StringBuilder();

	private char winner(char a, char b) {
		boolean hasR = (a == 'R' || b == 'R');
		boolean hasS = (a == 'S' || b == 'S');
		boolean hasP = (a == 'P' || b == 'P');
		if (hasR && hasS) {
			return 'R';
		} else if (hasS && hasP) {
			return 'S';
		} else if (hasP && hasR) {
			return 'P';
		}
		return a;
	}

	private static class EndException extends RuntimeException {
	}

	public void run() throws IOException {
		//        Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		nbTC = Integer.parseInt(reader.readLine());
//         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
			for (int tc = 1; tc <= nbTC; ++tc) {
				result.append(oneTestCase(reader));
				if (tc != nbTC) {
					result.append('\n');
				}
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

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

		String[] parts = reader.readLine().split(" ");
		int r = Integer.parseInt(parts[0]);
		int c = Integer.parseInt(parts[1]);
		int n = Integer.parseInt(parts[2]);

		char[][] world = new char[r][c];
		if (r == 0) {
			reader.readLine();
		}
		for (int i = 0; i < r; i++) {
			world[i] = reader.readLine().toCharArray();
		}
		int[] dx = {-1, +1, 0, 0};
		int[] dy = {0, 0, +1, -1};
		for (int i = 0; i < n; i++) {
			char[][] nextWorld = new char[r][c];
			for (int x = 0; x < r; x++) {
				System.arraycopy(world[x], 0, nextWorld[x], 0, c);
				for (int y = 0; y < c; y++) {
					loopdir:
					for (int dir = 0; dir < dx.length; dir++) {
						int nx = x + dx[dir];
						int ny = y + dy[dir];
						if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
							continue loopdir;
						}
						if (winner(world[x][y], world[nx][ny]) != world[x][y]) {
							nextWorld[x][y] = world[nx][ny];
						}
					}
				}
			}
			world = nextWorld;
		}
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				formatter.format("%c", world[x][y]);
			}
			formatter.format("%n");
		}
		if (r == 0) {
			formatter.format("%n");
		}
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
