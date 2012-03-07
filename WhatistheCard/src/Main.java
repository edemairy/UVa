
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input2.txt"));
        int nbTC = scanner.nextInt();
        scanner.nextLine();
        for (int testCase = 1; testCase <= nbTC; ++testCase) {
            String currentLine = scanner.nextLine();
            String result = oneTestCase(currentLine);
            System.out.println("Case " + testCase + ": " + result);
        }
    }

    private static String oneTestCase(String currentLine) {
        String result = "";
        Scanner scanner = new Scanner(currentLine);
        Pattern cardPattern = Pattern.compile("\\w\\w");
        Matcher matcher = cardPattern.matcher(currentLine);
        ArrayList<String> first27 = new ArrayList<String>();
        ArrayList<String> last25 = new ArrayList<String>();
        for (int i = 0; i < 27; ++i) {
            matcher.find();
            String card = matcher.group();
            first27.add(card);
        }
        int Y = 0;
        int pos = 26;
        for (int i = 0; i < 3; ++i) {
            if (pos > 0) {
                Y += val(first27.get(pos));
                pos -= (1 + (10 - val(first27.get(pos))));
            }
        }

        for (int i = pos + 1; i < pos + 1 + 25; ++i) {
            matcher.find();
            String card = matcher.group();
            if (i<=26) {
                first27.set(i, card);
            } else {
                first27.add(card);
            }
        }
        result = first27.get(Y-1);
        return result;
    }

    private static int val(final String card) {
        switch (card.charAt(0)) {
            case 'T':              
            case 'A':
            case 'J':
            case 'Q':
            case 'K':
                return 10;
        }
        return card.charAt(0) - '0';
    }
}
