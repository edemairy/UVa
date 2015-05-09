
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
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
//        nbTC = readInt(reader);
		nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
			for (int tc = 1; tc <= nbTC; ++tc) {
				System.out.format(oneTestCase(reader).toString());
				System.out.format("%n");
			}
		} catch (EndException e) {
		}
	}

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
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
		int A = Integer.parseInt(parts[0]);
		int B = Integer.parseInt(parts[1]);
		int C = Integer.parseInt(parts[2]);
		int X = Integer.parseInt(parts[3]);
		int Y = Integer.parseInt(parts[4]);
		if (A == 0 && B == 0 && C == 0 && X == 0 && Y == 0) {
			throw new EndException();
		}
		ArrayList<Integer> princess = new ArrayList<Integer>();
		ArrayList<Integer> prince = new ArrayList<Integer>();
		BitSet bs = new BitSet(53);
		bs.set(1, 53);
		bs.clear(A);
		bs.clear(B);
		bs.clear(C);
		bs.clear(X);
		bs.clear(Y);

		for (int card = 1; card <= 52; card++) {
			if (!bs.get(card)) {
				continue;
			}
			princess.clear();
			prince.clear();
			princess.add(A);
			princess.add(B);
			princess.add(C);
			prince.add(X);
			prince.add(Y);
			prince.add(card);
			Collections.sort(princess);
			Collections.sort(prince);

			int win = 0;
			loopi:
			for (int i = 2; i >= 0; i--) {
				for (int j = 0; j < princess.size(); j++) {
					if (princess.get(j) > prince.get(i)) {
						princess.remove(j);
						continue loopi;
					}
				}
				princess.remove(0);
				win++;
			}
			if (win >= 2) {
				output.append(card);
				return output;
			}
		}
		output.append(-1);
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
