
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
        reader.readLine();
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            if (tc!=nbTC) result.append('\n');
        }
        System.out.print(result);
    }

    private static int searchf(int[] f, int s) {
        while (s != f[s]) {
            s = f[s];
        }
        return s;
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        int N = readInt(reader);
        int[] f = new int[N+1];
        for (int i = 0; i <= N; i++) {
            f[i] = i;
        }
        String current = reader.readLine();
        int nbok = 0;
        int nbko = 0;
        while (current != null && !current.isEmpty()) {
            String[] parts = current.split(" ");
            int s = Integer.parseInt(parts[1]);
            int e = Integer.parseInt(parts[2]);
            switch (parts[0].charAt(0)) {
                case 'c': {
                    int fs = searchf(f, s);
                    int fe = searchf(f, e);
                    f[fs] = fe;
                    break;
                }
                case 'q': {
                    int fs = searchf(f, s);
                    int fe = searchf(f, e);
                    if (fs == fe) {
                        nbok++;
                    } else {
                        nbko++;
                    }
                }
            }
            current = reader.readLine();
        }
        formatter.format("%d,%d\n", nbok, nbko);
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        output.append(formatter.out());
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
