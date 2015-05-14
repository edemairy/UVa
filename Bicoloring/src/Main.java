
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

	public static class Graph {

		private BitSet[] graph;

		public Graph(int n) {
			graph = new BitSet[n];
			for (int i = 0; i<n; i++) {
				graph[i] = new BitSet(n);
			}
		}

		public void addEdge(int s, int e) {
			graph[s].set(e);
		}

		public int[] getNeighbours(int n) {
			int[] result = new int[graph[n].cardinality()];
			for (int i = graph[n].nextSetBit(0), cpt = 0; i != -1; i = graph[n].nextSetBit(i + 1), cpt++) {
				result[cpt] = i;
			}
			return result;
		}
	}

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();
		int n = readInt(reader);
		if (n == 0) {
			throw new EndException();
		}
		Graph graph = new Graph(n);
		int m = readInt(reader);
		for (int i = 0; i < m; i++) {
			graph.addEdge(readInt(reader), readInt(reader));
		}
		int[] color = new int[n];
		Arrays.fill(color, 0);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
		boolean isBicolor = true;
		color[0] = 1;
		int curColor = 2;
		int oColor = 1;
		mainloop:
		while (!queue.isEmpty()) {
			ArrayList<Integer> nextqueue = new ArrayList<Integer>();
			while (!queue.isEmpty()) {
				int cur = queue.remove(queue.size() - 1);
				int[] neighbours = graph.getNeighbours(cur);
				for (int i = 0; i < neighbours.length; i++) {
					int neighbour = neighbours[i];
					if (color[neighbour] == 0) {
						color[neighbour] = curColor;
						nextqueue.add(neighbour);
					} else if (color[neighbour] != curColor) {
						isBicolor = false;
						break mainloop;
					}
				}
			}
			if (curColor == 1) {
				curColor =2;
			} else {
				curColor =1;
			}
			queue = nextqueue;
		}
		formatter.format("%s", isBicolor ? "BICOLORABLE." : "NOT BICOLORABLE.");
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
