
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private static int nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    public static int[][] city = new int[5][5];
    public static int[] best = new int[5];
    public static int bestCost;

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        bestCost = Integer.MAX_VALUE;
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; ++i) {
            Arrays.fill(city[i], 0);
        }
        int nb = readInt(reader);
        for (int i = 0; i < nb; ++i) {
            int a = readInt(reader);
            int b = readInt(reader);
            city[a][b] = readInt(reader);
        }
        for (int i1 = 0; i1 < 25; ++i1) {
            for (int i2 = i1 + 1; i2 < 25; ++i2) {
                for (int i3 = i2 + 1; i3 < 25; ++i3) {
                    for (int i4 = i3 + 1; i4 < 25; ++i4) {
                        for (int i5 = i4 + 1; i5 < 25; ++i5) {
                            int cost = 0;
                            for (int a = 0; a < 5; ++a) {
                                for (int b = 0; b < 5; ++b) {
                                    cost += city[a][b] * Math.min(
                                            dist(i1, a, b), Math.min(
                                            dist(i2, a, b), Math.min(
                                            dist(i3, a, b), Math.min(
                                            dist(i4, a, b), dist(i5, a, b)))));

                                }
                            }
                            if (cost < bestCost) {
                                bestCost = cost;
                                best[0] = i1;
                                best[1] = i2;
                                best[2] = i3;
                                best[3] = i4;
                                best[4] = i5;
                            }
                        }
                    }
                }
            }
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        formatter.format("%d %d %d %d %d", new Object[]{best[0], best[1], best[2], best[3], best[4]});
        result.append(formatter.out());
        return result;
    }

    public static int dist(int i, int a, int b) {
        int ia = i / 5;
        int ib = i % 5;
        int result = (Math.abs(ia - a) + Math.abs(ib - b));
        return result;
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
