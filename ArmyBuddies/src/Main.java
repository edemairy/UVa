
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

	private int nbTC;

	private static class EndException extends RuntimeException {
	}

	public void run() throws IOException {
		//        Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer
			= new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder output = new StringBuilder(25000);
//        nbTC = readInt(reader);
		nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
			for (int tc = 1; tc <= nbTC; ++tc) {
				oneTestCase(reader, output);
				output.append("-\n");
			}
		} catch (EndException e) {
		}
		writer.write(output.toString());
		writer.flush();
		writer.close();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.run();
	}
	private static int MAX_SOLDIERS = 100000;
	private static int[] leftNeighbour = new int[MAX_SOLDIERS + 2];
	private static int[] rightNeighbour = new int[MAX_SOLDIERS + 2];

	private void oneTestCase(BufferedReader reader, StringBuilder output) throws IOException {
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
		int nbSoldiers = readInt(reader);
		int nbReports = readInt(reader);
		if (nbSoldiers == 0 || nbReports == 0) {
			throw new EndException();
		}
//		for (int soldier = 1; soldier <= nbSoldiers; soldier++) {
//			leftNeighbour[soldier] = soldier - 1;
//			rightNeighbour[soldier] = soldier + 1;
//		}
		Arrays.fill(leftNeighbour, -1);
		Arrays.fill(rightNeighbour, -1);
		for (int r = 0; r < nbReports; r++) {
			int left = readInt(reader);
			int right = readInt(reader);
			// lazy initialization 
			if (leftNeighbour[left] == -1) {
				leftNeighbour[left] = left - 1;
			}
			if (rightNeighbour[right] == -1) {
				rightNeighbour[right] = right + 1;
			}
			//
			int newRight = rightNeighbour[leftNeighbour[left]] = rightNeighbour[right];
			int newLeft = leftNeighbour[rightNeighbour[right]] = leftNeighbour[left];
			if (newLeft <= 0) {
				output.append("*");
			} else {
				output.append(newLeft);
			}
			output.append(" ");
			if (newRight > nbSoldiers) {
				output.append("*");
			} else {
				output.append(newRight);
			}
			output.append("\n");
		}
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
