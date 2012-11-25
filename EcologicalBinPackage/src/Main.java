
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        scanner.nextLine();
        try {
            while (true) {
                result.append(oneTestCase(reader));
//                result.append('\n');
            }
        } catch (IOException e) {
        }
        System.out.print(result);
    }
    static char[] letters = {'B', 'G', 'C'};

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        int[][] bins = new int[3][3];
        for (int bin = 0; bin < 3; ++bin) {
            for (int t = 0; t < 3; ++t) {
                bins[bin][t] = readInt(reader);
            }
        }
        int[][] mv = new int[3][3];
        for (int t = 0; t < 3; ++t) {
            for (int bin = 0; bin < 3; ++bin) {
                mv[t][bin] = 0;
                for (int k = 0; k < 3; ++k) {
                    if (bin != k) {
                        mv[t][bin] += bins[k][t];
                    }
                }
            }
        }
        int br = Integer.MAX_VALUE;
        TreeSet<String> sols = new TreeSet<String>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                loopk:
                for (int k = 0; k < 3; k++) {
                    if ((i != j) && (i != k) && (j != k)) {
                        int cost = mv[0][i] + mv[1][j] + mv[2][k];
                        if (cost > br) {
                            continue loopk;
                        }
                        if (cost < br) {
                            sols.clear();
                        }
                        br = cost;
                        StringBuilder name = new StringBuilder("   ");
                        name.setCharAt(i, letters[0]);
                        name.setCharAt(j, letters[1]);
                        name.setCharAt(k, letters[2]);
                        
                        sols.add(name.toString());
                    }
                }

            }
        }

        output.append(sols.first() + " " + br + "\n");
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

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
