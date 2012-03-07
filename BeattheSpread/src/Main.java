
import java.util.Formatter;
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
        int aplusb = scanner.nextInt();
        int aminusb = scanner.nextInt();
        int doublea = aplusb + aminusb;
        int a = doublea / 2;
        int doubleb = aplusb - aminusb;
        int b = doubleb / 2;
        boolean possible = true;
        if ((doublea % 2 == 1) || (doubleb % 2 == 1) || (doublea < 0) || (doubleb < 0)) {
            possible = false;
        }
        
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        if (possible) {
            result.append(Math.max(a, b)).append(" ").append(Math.min(a,b));
        } else {
            result.append("impossible");
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }
}
