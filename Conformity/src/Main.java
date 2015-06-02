
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;
import java.util.TreeMap;
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

		int n = readInt(reader);
		if (n == 0) {
			throw new EndException();
		}
		TreeMap<TreeSet<Integer>, Integer> list = new TreeMap<>(new Comparator<TreeSet<Integer>>() {

			@Override
			public int compare(TreeSet<Integer> o1, TreeSet<Integer> o2) {
				Integer[] v1 = o1.toArray(new Integer[0]);
				Integer[] v2 = o2.toArray(new Integer[0]);
				for (int i = 0; i < 5; i++) {
					if (v1[i].compareTo(v2[i]) != 0) {
						return v1[i] - v2[i];
					}
				}
				return 0;
			}
		});
		int max = 0;
		for (int i = 0; i < n; i++) {
			TreeSet<Integer> newList = new TreeSet<>();
			for (int j = 0; j < 5; j++) {
				newList.add(readInt(reader));
			}
			if (!list.containsKey(newList)) {
				list.put(newList, 0);
			}
			list.put(newList, list.get(newList) + 1);
			max = Math.max(max, list.get(newList));
		}
		int r = 0;
		for (int v : list.values()) {
			if (v == max) {
				r += v;
			}
		}
		formatter.format("%d%n", r);
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
