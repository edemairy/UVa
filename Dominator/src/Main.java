
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TreeSet;

/**
 *
 * @author edemairy
 */
public class Main {

    private static int nbTC;
    private StringBuilder result = new StringBuilder();

    public void run() throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append("Case ").append(tc).append(":\n");
            result.append(oneTestCase(reader));
//            result.append('\n');
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

    private static class Graph {

        private TreeSet<Integer>[] data;

        public Graph(int N) {
            this.size = N;
            data = new TreeSet[N];
            for (int i = 0; i < N; i++) {
                data[i] = new TreeSet<Integer>();
            }
        }

        public void reset(int N) {
            for (int i = 0; i < N; ++i) {
                data[i].clear();
            }
            size = N;
        }

        public void addEdge(int s, int e) {
            data[s].add(e);
        }
        boolean[] seen, reach;
        private int size;
        private Integer[][] dataS;

        private void init() {
            dataS = new Integer[size][];
            for (int i = 0; i < size; i++) {
                dataS[i] = data[i].toArray(new Integer[0]);
            }
        }

        private void reachable(StringBuilder output) {
            init();
            reach = new boolean[size];
            Arrays.fill(reach, false);
            output.append('|');

            list.push(0);
            while (!list.isEmpty()) {
                int current = list.pop();
                reach[current] = true;;
                for (int next : dataS[current]) {
                    if (!reach[next]) {
                        list.push(next);
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                output.append(reach[i] ? "Y" : "N").append("|");
            }
            output.append("\n");
        }
        private LinkedList<Integer> list = new LinkedList<Integer>();

        private void reachableWithout(int i, StringBuilder output) {
            if (!reach[i]) {
                output.append("|");
                for (int j = 0; j < size; j++) {
                    output.append("N|");
                }
                output.append("\n");
                return;
            }
            seen = new boolean[size];
            output.append("|");
            Arrays.fill(seen, false);
            list.push(0);
            while (!list.isEmpty()) {
                int current = list.pop();
                seen[current] = true;
                for (int next : dataS[current]) {
                    if (next != i && !seen[next]) {
                        list.push(next);
                        seen[next] = true;
                    }
                }
            }
            for (int j = 0; j < size; j++) {
                output.append((seen[j] || !reach[j]) ? "N" : "Y").append("|");
            }
            output.append("\n");
        }
    }
    private Graph g = new Graph(100);

    private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();


        int N = Integer.parseInt(reader.readLine());
        g.reset(N);
        StringBuilder separator = new StringBuilder(2 * N + 1);

        separator.append("+");
        for (int j = 0; j < 2 * N - 1; j++) {
            separator.append("-");
        }
        separator.append("+\n");

        for (int i = 0; i < N; i++) {
            String cline = reader.readLine();
            String[] parts = cline.split(" ");
            for (int j = 0; j < parts.length; ++j) {
                if (parts[j].charAt(0) == '1') {
                    g.addEdge(i, j);
                }
            }
        }

        output.append(separator);
        g.reachable(output);



        for (int i = 1; i < N; i++) {
            output.append(separator);
            g.reachableWithout(i, output);
        }
        output.append(separator);


        return output;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
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
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return result;
        } else {
            return -result;
        }
    }
}
