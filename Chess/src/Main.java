
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
//          Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));
        int nbTC = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= nbTC; ++i) {
            int result = oneTestCase(scanner);
            System.out.println(result);
        }
    }

    private static int oneTestCase(Scanner scanner) {
        int result = 0;
        char type = scanner.next().charAt(0);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        if ((m==0)||(n==0)) return 0;
        switch (type) {
            case 'Q':
            case 'r':
                return Math.min(m, n);
            case 'k':
                if (Math.min(m, n) > 3) {
                    return ((m * n + 1) >> 1);
                } else {
                    int t = Math.max(m, n);
                    m = Math.min(m, n);
                    n = t;
                    if (m == 1) {
                        return n;
                    }
                    if (m == 2) {
                        int r = 4 * (n / 4);
                        if (n % 4 == 1) {
                            r += 2;
                        }
                        if (n % 4 >= 2) {
                            r += 4;
                        }
                        return r;
                    }
                }
            case 'K': {
                int t = Math.max(m, n);
                m = Math.min(m, n);
                n = t;
                return (1+(m-1)/2)*(1+(n-1)/2);
            }


        }
        return result;
    }
}
