
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;
import java.util.TreeSet;

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

		int round = Integer.parseInt(reader.readLine());
		if (round == -1) {
			throw new EndException();
		}
		String solution = reader.readLine();
		TreeSet<Character> solLetters = new TreeSet<>();
		for (char c : solution.toCharArray()) {
			solLetters.add(c);
		}
		String player = reader.readLine();
		int wrong = 0;
		TreeSet<Character> found = new TreeSet<>();
		TreeSet<Character> wrongLetters = new TreeSet<>();
		boolean foundSol = false;
		for (char c : player.toCharArray()) {
			if (solLetters.contains(c)) {
				found.add(c);
				if (found.size() == solLetters.size() && wrong < 7) {
					foundSol = true;
				}
			} else {
				if (wrongLetters.contains(c)) {
					// nothing
				} else {
					wrong++;
					wrongLetters.add(c);
				}
			}
		}
		formatter.format("Round %d%n", round);
		if (foundSol) {
			formatter.format("You win.");
		} else if (wrong >= 7) {
			formatter.format("You lose.");
		} else {
			formatter.format("You chickened out.");
		}

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
