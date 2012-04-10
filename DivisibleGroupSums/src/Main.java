
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
        N = readInt(reader);
        int Q = readInt(reader);

        nbTC = 1;
        while ((N != 0) || (Q != 0)) {
//        scanner.nextLine();        

            result.append(oneTestCase(Q, reader));
//            result.append('\n');
            ++nbTC;
            N = readInt(reader);
            Q = readInt(reader);
        }
        System.out.print(result);
    }
    private static int N;
    private static int[] numbers;

    private static StringBuilder oneTestCase(int Q, BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();

        numbers = new int[N];

        for (int i = 0; i < N; ++i) {
            numbers[i] = readInt(reader);
        }
        result.append("SET ").append(nbTC).append(":\n");
        for (int q = 1; q <= Q; ++q) {
            int d = readInt(reader);
            int m = readInt(reader);
            for (int i = 0; i < 201; ++i) {
                for (int j = 0; j < 201; ++j) {
                    Arrays.fill(cnbdivs[i][j],-1);                  
                }
            }
            int nbdivs = computeNbDivs(0, d, m, 0);
            result.append("QUERY ").append(q).append(": ").append(nbdivs).append("\n");
        }

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
    private static int[][][] cnbdivs = new int[201][201][21];

    private static int computeNbDivs(int i, int d, int m, int sum) {
        if (cnbdivs[i][m][sum] != -1) {
            return cnbdivs[i][m][sum];
        }
        int result = 0;
        if (m == 0) {
            if (sum % d == 0) {
                result = 1;
            }
        } else {
            if (i < N) {
                result += computeNbDivs(i + 1, d, m, sum);
                int s = (sum + numbers[i]) % d;
                if (s < 0) {
                    s += d;
                }
                result += computeNbDivs(i + 1, d, m - 1, s);
            } else {
                result = 0;
            }
        }
        cnbdivs[i][m][sum] = result;
        return result;
    }
}
