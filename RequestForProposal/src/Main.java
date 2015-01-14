
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

	class Proposal implements Comparable<Proposal> {

		private String name;
		private double price;
		private int nbRequirements;

		public Proposal(String name, double price, int nbRequirements) {
			this.name = name;
			this.nbRequirements = nbRequirements;
			this.price = price;
		}

		@Override
		public int compareTo(Proposal o) {
			if (this.nbRequirements == o.nbRequirements) {
				return (this.price < o.price) ? 1 : -1;
			} else {
				return this.nbRequirements - o.nbRequirements;
			}
		}
	}

	private int nbTC;
	private StringBuilder result = new StringBuilder();

	private static class EndException extends RuntimeException {
	}

	public void run() throws IOException {
		//        Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		nbTC = 1;//readInt(reader);
//         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
		try {
				result.append(oneTestCase(reader));
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
		int tc = 1;
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
		TreeSet<String> requirements = new TreeSet<String>();
		TreeSet<Proposal> proposals = new TreeSet<Proposal>();

		String[] parts = reader.readLine().split(" ");
		int n = Integer.parseInt(parts[0]);
		int p = Integer.parseInt(parts[1]);
		while (n != 0 || p != 0) {
			for (int i = 0; i < n; i++) {
				requirements.add(reader.readLine());
			}
			for (int i = 0; i < p; i++) {
				String nameProposal = reader.readLine();
				parts = reader.readLine().split(" ");
				double d = Double.parseDouble(parts[0]);
				int nbr = Integer.parseInt(parts[1]);
				proposals.add(new Proposal(nameProposal, d, nbr));
				for (int j = 0; j < nbr; j++) {
					reader.readLine();
				}
			}
			formatter.format("RFP #%d\n%s\n", tc, proposals.last().name);
			parts = reader.readLine().split(" ");
			tc++;
			n = Integer.parseInt(parts[0]);
			p = Integer.parseInt(parts[1]);
			proposals.clear();
			requirements.clear();
			if (n!=0 || p != 0) {
				formatter.format("\n");
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
