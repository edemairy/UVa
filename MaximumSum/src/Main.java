
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


        result.append(oneTestCase(reader));
        result.append('\n');

        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {

        int nbInts = readInt(reader);
        int[][] grid = new int[nbInts + 1][nbInts + 1];

        int[][] sumGrid = new int[nbInts + 1][nbInts + 1];
        for (int i = 0; i <= nbInts; ++i) {
            Arrays.fill(grid[i], 0);
            Arrays.fill(sumGrid[i], 0);
        }
        for (int i = 1; i <= nbInts; ++i) {
            for (int j = 1; j <= nbInts; ++j) {
                grid[i][j] = readInt(reader);
                for (int k = i; k <= nbInts; ++k) {
                    for (int l = j; l <= nbInts; ++l) {
                        sumGrid[k][l] += grid[i][j];
                    }
                }
            }
        }
        int r = 0;
        for (int i = 1; i <= nbInts; ++i) {
            for (int j = 1; j <= nbInts; ++j) {
                for (int k = i; k  <= nbInts; ++k) {
                    for (int l = j; l  <= nbInts; ++l) {
                        r = Math.max(r, sumGrid[k][l] + sumGrid[i - 1][j - 1] - sumGrid[k][j-1] - sumGrid[i-1][l]);
                    }
                }
            }
        }

        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        result.append(r);
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
