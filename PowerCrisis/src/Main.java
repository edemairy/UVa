
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
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));
        while (scanner.hasNextLine()) {
            int N = scanner.nextInt();
            if (N == 0) {
                return;
            }
            int result = oneTestCase(N);
            System.out.println(result);
        }
    }

    private static int oneTestCase(int N) {
        int result = 0;
        int i = 1;
        while (true) {
            int p = 0;
            int nbi = 0;
            int lastp = 0;
            ArrayList<Integer> map = new ArrayList<Integer>();
            for (int m = 1; m <= N; ++m) {
                map.add(m);
            }
            int v = -1;
            emptymap:
            while (!map.isEmpty()) {
                v = map.get(p);
                map.remove(p);
                if ((v == 13) || (map.isEmpty())) {
                    break emptymap;
                }
                p = (p - 1 + i) % map.size();
            }
            if ((v == 13) && map.isEmpty()) {
                return i;
            }
            i++;
        }
    }
}
