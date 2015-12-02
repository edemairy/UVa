
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

	private int recurs(int i, LinkedList<Integer> visited, int[] vals, BitSet visiting, Graph g) {
		if (vals[i] != -1) {
			return vals[i];
		}
		if (visiting.get(i)) {
			int lcycle = 0;
			for (int j=visited.size()-1; j>=0; j--) {
				if (visited.get(j) == i) {
					lcycle = visited.size() - j; 
				}
			}
			vals[i] = 0;
		} else {
			visiting.set(i);
			vals[i] = 1 + recurs(g.getNeighbours(i)[0], vals, visiting, g);
		}
		return vals[i];
	}

	/**
	 *
	 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
	 */
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
				result.append("Case " + tc + ": ");
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

	int[] next;
	BitSet visited;
	LinkedList<Integer> stack = new LinkedList<>();
	int[] accessible;

	private void dfs(int num){
		visited.set(num);
		stack.addLast(num);
		if (!visited.get(next[num])) {
			dfs(next[num]);
		} else {
			for (int j=stack.size()-1; j>=0; j--) {
				if (stack.get(j) == num) {
					for (int k=j; k<stack.size(); k++) {
						accessible[k] = stack.size()-j;
					}
				}
			}	
		}
	}

	private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
		int n = readInt(reader);
		next = new int[n];
		accessible = new int[n];
		visited = new BitSet(n);
		for (int i = 0; i < n; i++) {
			int s = readInt(reader) - 1;
			int e = readInt(reader) - 1;
			next[s] = e;
		}
		int pos = 0;
		int maxaccessible = 0;
		for (int i = 0; i < n; i++) {
				dfs(i);
				if (accessible[i] > maxaccessible) {
					maxaccessible = accessible[i];
					pos = i;
				}
		}
		formatter.format("%d", pos + 1);
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
