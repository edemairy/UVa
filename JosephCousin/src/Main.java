
import java.util.ArrayList;
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
    public static ArrayList<Integer> primes = new ArrayList<Integer>();

    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));
        int testCase = 1;
        int N;

        int i = 1;
        searchp:
        while (primes.size() < 3510) {
            i++;
            fori:
            for (int a : primes) {
                if (i % a == 0) {
                    continue searchp;
                }
            }
            primes.add(i);
        }

        while ((N = scanner.nextInt()) != 0) {
            int result = oneTestCase(N);
            System.out.println(result);
            ++testCase;
        }
    }

    private static int oneTestCase(int N) {
        int result = 0;
        ArrayList<Integer> map = new ArrayList<Integer>();
        for (int i=1; i<=N; ++i) {
            map.add(i);
        }
        
        int pos=1;
        for (int i=1; i<N; ++i) {
            map.remove(pos);
            pos = (pos + primes.get(i)-1) % map.size();
        }
        
        return map.get(0);
    }
}
