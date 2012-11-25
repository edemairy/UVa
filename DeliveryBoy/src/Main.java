
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author edemairy
 */
public class Main {

    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        nbTC = readInt(reader);
//        scanner.nextLine();
//        for (int tc = 1; tc <= nbTC; ++tc) {
        result.append(oneTestCase(reader));
//        result.append('\n');
//        }
        System.out.print(result);
    }
    static int[][] T = new int[251][251];
    static int[][] minpath = new int[251][251];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        int N = readInt(reader);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; j++) {
                T[i][j] = readInt(reader);
                minpath[i][j] = T[i][j];
            }
        }

        boolean cont = true;
//        while (cont) {
        cont = false;
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; j++) {
                    if (minpath[i][k] + minpath[k][j] < minpath[i][j]) {
                        minpath[i][j] = minpath[i][k] + minpath[k][j];
                        cont = true;
                    }
                }
            }
        }
//        }

        int M = readInt(reader);
        for (int i = 0; i < M; i++) {
            int S = readInt(reader);
            int G = readInt(reader);
            int D = readInt(reader);
            formatter.format("%d %d\n", minpath[S][G] + minpath[G][D], minpath[S][G] + minpath[G][D] - minpath[S][D]);
        }
        output.append(formatter);
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
