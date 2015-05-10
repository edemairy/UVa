
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author edemairy
 */
public class Main {

	public static class Anagram implements Comparable<Anagram> {

		Map<Character, Integer> frequency = new HashMap<>();

		public Anagram(String s) {
			for (char c = 'a'; c <= 'z'; c++) {
				frequency.put(c, 0);
			}
			for (char c = 'A'; c <= 'Z'; c++) {
				frequency.put(c, 0);
			}
			for (char c :s.toCharArray()) {
				if (c >= 'a' && c <= 'z') {
					frequency.put(c, frequency.get(c) + 1);
				}
				if (c >= 'A' && c <= 'Z') {
					frequency.put(c, frequency.get(c) + 1);
				}
			}
		}

		@Override
		public int compareTo(Anagram o) {
			if (frequency.equals(o.frequency)) {
				return 0;
			}
			for (char c : frequency.keySet()) {
				int diff = frequency.get(c) - o.frequency.get(c);
				if (diff != 0) {
					return diff;
				}
			}
			return 0;
		}

	}

	private int nbTC;
	private StringBuilder result = new StringBuilder();

	private static class EndException extends RuntimeException {
	}

	public void run() throws IOException {
		//        Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		nbTC = Integer.parseInt(reader.readLine());
		reader.readLine(); // to get rid of a useless blank line.

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
		String line;
		ArrayList<String> strings = new ArrayList<String>();
		while ((line = reader.readLine()) != null && !line.isEmpty()) {
			strings.add(line);
		}
		Collections.sort(strings);
		ArrayList<Anagram> anags = new ArrayList<Anagram>();
		for (int i = 0; i < strings.size(); i++) {
			anags.add(new Anagram(strings.get(i)));
		}
		for (int i = 0; i < strings.size(); i++) {
			for (int j = i + 1; j < strings.size(); j++) {
				if (anags.get(i).compareTo(anags.get(j)) == 0) {
					formatter.format("%s = %s%n", strings.get(i), strings.get(j));
				}
			}
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
