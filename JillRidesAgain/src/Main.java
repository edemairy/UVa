
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
            result.append(oneTestCase(tc, reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    public static int[] paths = new int[20010 + 1];
    public static int[] sumPaths = new int[20010 + 1];

    private static StringBuilder oneTestCase(int nbTestCase, BufferedReader reader) throws IOException {
        int sizePath = readInt(reader);

//        Arrays.fill(paths, 0);
        paths[0] = 0;
        sumPaths[0] = 0;
        int mins = Integer.MAX_VALUE;
        int maxs = Integer.MIN_VALUE;
        for (int i = 1; i < sizePath; ++i) {
            paths[i] = readInt(reader);
            sumPaths[i] = sumPaths[i - 1] + paths[i];
        }
        int r = 0;
        int ri = -1;
        int rj = -1;
        int cri = 0;

        fori:
        for (int i = 0; i < sizePath; ++i) {
            if ((sumPaths[i] - sumPaths[cri]) < 0) {
                cri = i;
            } else {
                if ((sumPaths[i] - sumPaths[cri]) > r) {
                    ri = cri;
                    rj = i;
                    r = (sumPaths[i] - sumPaths[cri]);
                } else if ((sumPaths[i] - sumPaths[cri]) == r) {
                    if (cri > ri) {
                        continue fori;
                    }
                    if (i > rj) {
                        ri = cri;
                        rj = i;
                        r = (sumPaths[i] - sumPaths[cri]);
                    }
                }
            }
        }

        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        if (r == 0) {
            result.append("Route " + nbTestCase + " has no nice parts");
        } else {
            result.append("The nicest part of route " + nbTestCase + " is between stops " + (ri + 1) + " and " + (rj + 1));
        }
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
