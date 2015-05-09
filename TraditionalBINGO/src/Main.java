
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
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
//         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
			for (int tc = 1; tc <= nbTC; ++tc) {
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

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();
		int[][] card = new int[5][5];
		BitSet[] checked = new BitSet[5];
		for (int i = 0; i < 5; i++) {
			checked[i] = new BitSet(5);
			for (int j = 0; j < 5; j++) {
				if (i != 2 || j != 2) {
					card[i][j] = readInt(reader);
				} else {
					checked[i].set(j);
					card[i][j] = -1;
				}
			}
		}
		int[] numbers = new int[75];
		for (int i = 0; i < 75; i++) {
			numbers[i] = readInt(reader);
		}
		int bingo = -1;
		mainloop:
		for (int i = 0; i < 75; i++) {
			int number = numbers[i];
			for (int r = 0; r < 5; r++) {
				for (int c = 0; c < 5; c++) {
					if (card[r][c] == number) {
						checked[r].set(c);
						if (checked[r].cardinality() == 5
							|| (checked[0].get(c) && checked[1].get(c) && checked[2].get(c) && checked[3].get(c) && checked[4].get(c))
							|| (checked[0].get(0) && checked[1].get(1) && checked[3].get(3) && checked[4].get(4))
							|| (checked[4].get(0) && checked[3].get(1) && checked[1].get(3) && checked[0].get(4))) {
							bingo = i + 1;
							break mainloop;
						}
					}
				}
			}
		}
		formatter.format("BINGO after %d numbers announced", bingo);
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
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
