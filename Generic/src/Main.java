
import java.io.BufferedReader;
import java.io.IOException;
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
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        nbTC = scanner.nextInt();
        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(scanner));
            result.append('\n');
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(Scanner scanner) {
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }

        private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        char currentChar = (char) reader.read();
        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        return result;
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
