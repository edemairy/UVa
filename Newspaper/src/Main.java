
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
    private static HashMap<Character, Integer> chars = new HashMap<Character, Integer>(100);

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        int nbLetters = readInt(reader);
        chars.clear();

        for (int i = 0; i < nbLetters; ++i) {
            chars.put(readChar(reader), readInt(reader));
        }

        int nbLines = readInt(reader);
        long val = 0;
        fori:
        for (int i = 0; i < nbLines; ++i) {
            while (true) {
                char currentChar = readChar(reader);
                if (currentChar == '\n') {
                    continue fori;
                }
                val += (chars.containsKey(currentChar)) ? chars.get(currentChar) : 0;
            }
        }

        Formatter formatter = new Formatter();
        formatter.format("%d.%02d$", val / 100, val % 100);

        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//        }
        result.append(formatter.out());
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
