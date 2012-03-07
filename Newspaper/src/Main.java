
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
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        nbTC = scanner.nextInt();

        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(scanner));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static HashMap<Character, Integer> chars = new HashMap<Character, Integer>(100);

    private static StringBuilder oneTestCase(Scanner scanner) {
        int nbLetters = scanner.nextInt();
        chars.clear();

        for (int i = 0; i < nbLetters; ++i) {
            chars.put(scanner.next().charAt(0), scanner.nextInt());
        }

        int nbLines = scanner.nextInt();
        scanner.nextLine();
        long val = 0;
        for (int i = 0; i < nbLines; ++i) {
            String currentLine = scanner.nextLine();
            for (int c = 0; c < currentLine.length(); ++c) {
                char currenChar = currentLine.charAt(c);
                val += (chars.containsKey(currenChar)) ? chars.get(currenChar) : 0;
            }
        }

        Formatter formatter = new Formatter();
        formatter.format("%.2f$", val / 100.0);

        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//        }
        result.append(formatter.out());
        return result;
    }
}
