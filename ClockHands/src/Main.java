
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
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));
        int testCase = 1;
        Pattern pattern = Pattern.compile("^(\\d+):(\\d+)$");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            boolean b = matcher.matches();
            String gr0 = matcher.group(0);
            int hh = Integer.parseInt(matcher.group(1));
            int mm = Integer.parseInt(matcher.group(2));
            if ((hh == 00) && (mm == 00)) {
                return;
            } else {
                double anglemm = mm*6;
                double anglehh = hh*30+mm*0.5;
                double result = Math.max(anglehh,anglemm)-Math.min(anglehh,anglemm);
                result = Math.min(result, 360-result);
                System.out.printf("%3.3f\n", result);
            }
        }
    }

    private static int oneTestCase(String currentLine) {
        int result = 0;
        return result;
    }
}
