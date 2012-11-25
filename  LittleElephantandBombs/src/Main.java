
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author edemairy
 */
public class Main {

    private static int nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = scanner.nextInt();
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(scanner));
            result.append('\n');
        }
        System.out.print(result);
    }
    static public boolean[] r = new boolean[1010];

    private static StringBuilder oneTestCase(Scanner scanner) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        Arrays.fill(r, true);
        int N = scanner.nextInt();
        String line = scanner.next();
        for (int i = 0; i < N; ++i) {
            char c = line.charAt(i);
            if (c == '1') {
                if (i == 0) {
                    r[0] = r[1] = false;
                } else if (i == (N - 1)) {
                    r[i - 1] = r[i] = false;
                } else {
                    r[i - 1] = r[i] = r[i + 1] = false;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < N; ++i) {
            if (r[i]) {
                res++;
            }
        }
        output.append(res);
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
