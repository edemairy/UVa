
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        while ((m != 0) || (n != 0)) {
            int fresult = oneTestCase(m, n);
            System.out.println(fresult + " knights" + " may be placed on a " + m + " row " + n + " column board.");
            m = scanner.nextInt();
            n = scanner.nextInt();
        }
    }
    static final int MAXS = 510;
    static int[][] board = new int[MAXS][MAXS];

    private static int oneTestCase(int m, int n) {
        int t = Math.min(m, n);
        n = Math.max(m, n);
        m = t;
        if ((m < 3)) {
            if (m == 0) {
                return 0;
            }
            if (m == 1) {
                return n;
            }
            if (m >= 2) {
                int r = (4 * (n / 4));
                if (n % 4 == 1) {
                    r += 2;
                }
                if (n % 4 >= 2) {
                    r += 4;
                }
                return r;
            }
        } else {
            int cw = m * n;
            if (cw % 2 == 0) {
                return cw / 2;
            } else {
                return cw / 2 + 1;
            }
        }
        return -1;
    }
}
