
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
//            if (tc != nbTC) {
                result.append('\n');
//            }
        }
        System.out.print(result);
    }
    private static int[] party = new int[21];
    private static int[] r = new int[21];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Arrays.fill(party, 0);
        Arrays.fill(r, 0);
        int nbParty = readInt(reader);
        int votes = 0;
        for (int i = 1; i <= nbParty; ++i) {
            party[i] = readInt(reader);
            votes += party[i];
        }
        int maj = (votes ) / 2;
        for (int i = 0; i < (1 << nbParty); ++i) {
            int s = 0;
            for (int p = 1; p <= nbParty; ++p) {
                if ((i & (1 << (p - 1))) != 0) {
                    s += party[p];
                }
            }
            if (s > maj) {
                for (int p = 1; p <= nbParty; ++p) {
                    if ((i & (1 << (p - 1))) != 0) {
                        if ((s - party[p]) <= maj) {
                            ++r[p];
                        }
                    }
                }
            }
        }
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= nbParty; ++i) {
            formatter.format("party %d has power index %d\n", i, r[i]);
        }
        result.append(formatter.out());

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
