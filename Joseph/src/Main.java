
import java.util.ArrayList;
import java.util.Arrays;
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
    private static int maxM = 14;
    private static int[] m = new int[maxM];

    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        Arrays.fill(m, 0);
        int testCase = 1;
        int k = scanner.nextInt();
        while (k != 0) {
            System.out.println(oneTestCase(k));
            k = scanner.nextInt();
        }
    }
// 1 2 3 4 5 6

    private static int oneTestCase(int k) {

        if (m[k] == 0) {

            loop:
            for (int i = k; ; ++i) {
                ArrayList<Integer> g = new ArrayList<Integer>(2 * k);
                for (int j = 0; j < 2 * k; ++j) {
                    g.add(j);
                }
                logger.log(Level.INFO, "i={0}", i);
                int pos = -1;
                int nbd = 0;
                int died = -1;
                do {
                    ++nbd;
                    pos += i;
                    pos %= g.size();
                    logger.log(Level.INFO, "nbd={0}, pos={1}", new Object[]{nbd, pos});
                    died = g.remove(pos);
                    --pos;
                    pos %= g.size();
                    if (pos<0) pos+= g.size();
                } while ((nbd < k) && (died >= k));
                if ((nbd == k) && (died >= k)) {
                    m[k] = i;
                    break loop;
                }
            }
        }
        return m[k];
    }
}
