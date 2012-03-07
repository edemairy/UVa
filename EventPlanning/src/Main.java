
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
     *
     * The first line of input consists of four integers: 1 N 200 , the number
     * of participants, 1 B 500000 , the budget, 1 H 18 , the number of hotels
     * to consider, and 1 W 13 , the number of weeks you can choose between.
     * Then follow two lines for each of the H hotels. The first gives 1 p 10000
     * , the price for one person staying the weekend at the hotel. The second
     * contains W integers, 0 a 1000 , giving the number of available beds for
     * each weekend at the hotel.
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        int testCase = 1;
        while (scanner.hasNextLine()) {
            int N = scanner.nextInt();
            int B = scanner.nextInt();
            int H = scanner.nextInt();
            int W = scanner.nextInt();
            int[] p = new int[H];
            int[][] b = new int[H][W];
            for (int h = 0; h < H; ++h) {
                p[h] = scanner.nextInt();
                for (int w = 0; w < W; ++w) {
                    b[h][w] = scanner.nextInt();
                }
            }
            scanner.nextLine();
            int result = B+1;;
            logger.log(Level.INFO, "N={0}, B={1}, H={2}, W={3}, p={4}, b={5}", new Object[]{N,B,H,W,p,b});
            for (int h = 0; h < H; ++h) {
                for (int w = 0; w < W; ++w) {
                    if ( (b[h][w]>=N) ) {
                        result = Math.min(result, p[h]*N);
                    } 
                }
            }
            System.out.println(((result)<=B ? result : "stay home") );
            ++testCase;
        }
    }

    private static int oneTestCase(String currentLine) {
        int result = 0;
        return result;
    }
}
