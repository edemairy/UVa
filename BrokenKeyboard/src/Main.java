
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
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            result.append(oneTestCase(currentLine));
            result.append('\n');
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(String currentLine) throws IOException {
        int n = currentLine.length();
        LinkedList<Character> string = new LinkedList<Character>();
        int curPos = 0;
        for (int i = 0; i < n; ++i) {
            char curChar = currentLine.charAt(i);
            switch (curChar) {
                case '[':
                    curPos = 0;
                    break;
                case ']':
                    curPos = string.size();
                    break;
                default:
                    string.add(curPos++, curChar);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character c : string) {
            result.append(c);
        }
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

    private static char readNextChar(BufferedReader reader) throws IOException {
        char result;
        do {
            result = (char) reader.read();
        } while ((result == '\n') || (result == ' ') || (result == '\t'));
        return result;
    }
}
