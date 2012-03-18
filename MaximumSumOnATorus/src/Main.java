
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    final static int N = 75;
    static int[][] grid = new int[2 * N + 1][2 * N + 1];
    static int[][] sumGrid = new int[2 * N + 1][2 * N + 1];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        final int n = readInt(reader);
        Arrays.fill(grid[0], 0);
        for (int i = 1; i <= n; i++) {
            grid[i][0] = 0;
            Arrays.fill(sumGrid[i], 0);
            for (int j = 1; j <= n; j++) {
                grid[i][j] = grid[i + n][j + n] = grid[i][j + n] = grid[i + n][j] = readInt(reader);
            }
        }
        for (int i = 1; i <= 2 * n; i++) {
            Arrays.fill(sumGrid[i], 0);
        }
        Arrays.fill(sumGrid[0], 0);
        for (int i = 1; i <= 2 * n; i++) {
            sumGrid[i][0] = 0;
            for (int j = 1; j <= 2 * n; j++) {
                for (int k = i; k <= 2 * n; k++) {
                    for (int l = j; l <= 2 * n; l++) {
                        sumGrid[k][l] += grid[i][j];
                    }
                }
            }
        }
        int b = 0;
        for (int i = 1; i <= 2 * n; i++) {
            for (int j = 1; j <= 2 * n; j++) {
                for (int k = i; k <= Math.min(i + n - 1, 2 * n); k++) {
                    for (int l = j; l <= Math.min(j + n - 1, 2 * n); l++) {
                        b = Math.max(b, sumGrid[k][l] + sumGrid[i - 1][j - 1] - sumGrid[k][j - 1] - sumGrid[i - 1][l]);
                    }
                }
            }
        }

        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        result.append(b);
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
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
