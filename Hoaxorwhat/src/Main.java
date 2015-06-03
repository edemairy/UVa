
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static class MultiSet<E> {

		private TreeMap<E, Integer> data;

		public MultiSet() {
			data = new TreeMap<>();
		}

		public boolean add(E e) {
			boolean result = data.keySet().contains(e);
			if (!result) {
				data.put(e, 0);
			}
			data.put(e, data.get(e) + 1);
			return result;
		}

		public boolean remove(E e) {
			boolean result = data.keySet().contains(e);
			if (result) {
				data.put(e, data.get(e) - 1);
			}
			if (data.get(e) == 0) {
				data.remove(e);
			}
			return result;
		}

		public E pollFirst() {
			E firstKey = data.firstKey();
			remove(firstKey);
			return firstKey;
		}

		public E pollLast() {
			E lastKey = data.lastKey();
			remove(lastKey);
			return lastKey;
		}
	}

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

		int nbDays = readInt(reader);
		if (nbDays == 0) {
			throw new EndException();
		}
		long payed = 0;
		MultiSet<Integer> bills = new MultiSet<>();
		for (int d = 0; d < nbDays; d++) {
			int nbBills = readInt(reader);
			for (int bill = 0; bill < nbBills; bill++) {
				bills.add(readInt(reader));
			}
			payed += (bills.pollLast() - bills.pollFirst());
		}
		formatter.format("%d", payed);
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
