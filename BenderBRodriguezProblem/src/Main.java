
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

		int l = Integer.parseInt(reader.readLine());
		if (l == 0) {
			throw new EndException();
		}
		String[] parts = reader.readLine().split(" ");
		HashMap<String, HashMap<String, String>> table = new HashMap<>();
		String[] order = {"+x", "+y", "+z", "-x", "-y", "-z"};
		String[] rotpy = {"+y", "-x", "+z", "-y", "+x", "-z"};
		String[] rotmy = {"-y", "+x", "+z", "+y", "-x", "-z"};
		String[] rotpz = {"+z", "+y", "-x", "-z", "-y", "+x"};
		String[] rotmz = {"-z", "+y", "+x", "+z", "-y", "-x"};
		String curdir = "+x";
		for (String part : parts) {
			int o = Arrays.binarySearch(order, curdir);
			switch (part) {
				case "+y":
					curdir = rotpy[o];
					break;
				case "-y":
					curdir = rotmy[o];
					break;
				case "+z":
					curdir = rotpz[o];
					break;
				case "-z":
					curdir = rotmz[o];
					break;
			}
		}
		formatter.format(curdir);
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
