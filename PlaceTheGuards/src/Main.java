
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

	public static class Graph {

		private int n;
		private BitSet[] graph;

		public Graph(int n) {
			this.n = n;
			graph = new BitSet[n];
			for (int i = 0; i < n; i++) {
				graph[i] = new BitSet(n);
			}
		}

		public void addEdge(int s, int e) {
			graph[s].set(e);
			graph[e].set(s);
		}

		public int[] getNeighbours(int n) {
			int[] result = new int[graph[n].cardinality()];
			for (int i = graph[n].nextSetBit(0), cpt = 0; i != -1; i = graph[n].nextSetBit(i + 1), cpt++) {
				result[cpt] = i;
			}
			return result;
		}

		int getNodesNumber() {
			return n;
		}
	}

	public static class SolBisect {

		public int[] colors;
		ArrayList<ArrayList<Integer>> cfc;
	};

	public static SolBisect bisect(Graph g) {
		int n = g.getNodesNumber();
		ArrayList<ArrayList<Integer>> cfc = new ArrayList<>();
		int[] color = new int[n];
		Arrays.fill(color, 0);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
		boolean isBicolor = true;
		color[0] = 1;
		int curColor = 2;
		int oColor = 1;
		int numCfc = 0;
		cfc.add(new ArrayList<Integer>());
		cfc.get(0).add(0);
		mainloop:
		while (!queue.isEmpty()) {
			ArrayList<Integer> nextqueue = new ArrayList<Integer>();
			while (!queue.isEmpty()) {
				int cur = queue.remove(queue.size() - 1);
				int[] neighbours = g.getNeighbours(cur);
				for (int i = 0; i < neighbours.length; i++) {
					int neighbour = neighbours[i];
					if (color[neighbour] == 0) {
						cfc.get(numCfc).add(neighbour);
						color[neighbour] = curColor;
						nextqueue.add(neighbour);
					} else if (color[neighbour] != curColor) {
						isBicolor = false;
						break mainloop;
					}
				}
			}
			queue = nextqueue;
			if (queue.isEmpty()) {
				for (int i = 0; i < n; i++) {
					if (color[i] == 0) {
						color[i] = curColor;
						queue.add(i);
						cfc.add(new ArrayList<Integer>());
						numCfc++;
						cfc.get(numCfc).add(i);
						break;
					}
				}
			}
			if (curColor == 1) {
				curColor = 2;
			} else {
				curColor = 1;
			}
		}
		if (isBicolor) {
			SolBisect sol = new SolBisect();
			sol.colors = color;
			sol.cfc = cfc;
			return sol;
		} else {
			return null;
		}
	}

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();
		int n = readInt(reader);
		int m = readInt(reader);
		Graph graph = new Graph(n);
		for (int i = 0; i < m; i++) {
			int s = readInt(reader);
			int e = readInt(reader);
			graph.addEdge(s, e);
		}

		SolBisect sol = bisect(graph);
		if (sol == null) {
			formatter.format("-1");
		} else {
			int r = 0;
			for (int c = 0; c < sol.cfc.size(); c++) {
				int nb1 = 0;
				int nb2 = 0;
				for (int i : sol.cfc.get(c)) {
					if (sol.colors[i] == 1) {
						nb1++;
					} else {
						nb2++;
					}
				}
				if (nb1 == 0 || nb2 == 0) {
					r += Math.max(nb1, nb2);
				} else {
					r += Math.min(nb1, nb2);
				}
			}
			formatter.format("%d", r);
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
