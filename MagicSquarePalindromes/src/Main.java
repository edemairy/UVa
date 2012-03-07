
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
        int nbTC = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= nbTC; ++i) {
            String currentLine = scanner.nextLine();
            int result = oneTestCase(currentLine);
            System.out.println("Case #" + i + ":");
            if (result != -1) {
                System.out.println(result);
            } else {
                System.out.println("No magic :(");
            }
        }
    }

    private static int oneTestCase(String currentLine) {
        int result = 0;
        StringBuilder text = new StringBuilder();
        Pattern pat = Pattern.compile("\\W*(\\w*)\\W*");
        Matcher mat = pat.matcher(currentLine);
        while (mat.find()) {
            text.append(mat.group(1));
        }
        int n = text.length();
        int sqn = (int) Math.sqrt(n);
        if (sqn * sqn != n) {
            return -1;
        } else {
            for (int i = 0; i < sqn; ++i) {
                for (int j = 0; j < sqn; ++j) {
                    int pos11l2r = i * sqn + j;
                    int pos11t2b = i + sqn * j;
                    int poskkr2l = (sqn - i - 1) * sqn + (sqn - j - 1);
                    int poskkb2t = (sqn - i - 1) + (sqn - j - 1) * sqn;
                    if ((text.charAt(pos11l2r) == text.charAt(pos11t2b))
                            && (text.charAt(poskkb2t) == text.charAt(poskkr2l))
                            && (text.charAt(pos11l2r) == text.charAt(poskkr2l))) {
                        continue;
                    } else {
                        return -1;
                    }
                }
            }
            return sqn;
        }
    }
}
