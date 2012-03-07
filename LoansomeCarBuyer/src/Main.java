
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

        
        mainLoop:
        while(true) {
            /*
             * Each loan consists of one line containing the duration in months
             * of the loan, the down payment, the amount of the loan, and the
             * number of depreciation records that follow
             */
            int duration = scanner.nextInt();
            double down = Double.valueOf(scanner.next());
            double loanAmount = Double.valueOf(scanner.next());
            int nbRecords = scanner.nextInt();
            if (duration < 0) {
                break mainLoop;
            }
            result.append(oneTestCase(scanner, duration, down, loanAmount, nbRecords));
            result.append('\n');
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(Scanner scanner, int duration, double down, double loanAmount, int nbRecords) {
        double[] d = new double[duration + 1];
        int lm = scanner.nextInt();;
        double lp = Double.valueOf(scanner.next());
        d[lm] = lp;
        for (int r = 1; r < nbRecords; ++r) {
            int m = scanner.nextInt();
            double percent = Double.valueOf(scanner.next());
            for (int cm = lm; cm < m; ++cm) {
                d[cm] = lp;
            }
            lm = m;
            lp = percent;
            if (r == (nbRecords-1)) {
                for (int cm = lm; cm <= duration; ++cm) {
                    d[cm] = lp;
                }
            }
        }
        double value = (loanAmount+down) * (1-d[0]);
        double loan = loanAmount;
        double rent = loanAmount / duration;
        int m = 0;
        while (loan > value) {
            ++m;
            value = value * (1-d[m]);
            loan = loan - rent;
        }
        
        StringBuilder result = new StringBuilder();
        result.append(m).append(" month");
        if ((m)!=1) {
            result.append("s");
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }
}
