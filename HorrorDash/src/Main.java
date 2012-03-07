
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

        int nbTC = scanner.nextInt();
        scanner.nextLine();

        for (int tc=1; tc<=nbTC; ++tc) {
            String cl = scanner.nextLine();
            Scanner scanL = new Scanner(cl);
            int r = 0;
            while (scanL.hasNext()) {
                r = Math.max(r, scanL.nextInt());
            }
            System.out.println("Case "+tc+": "+r);
        }
    }

    private static int oneTestCase(String currentLine) {
        int result = 0;
        return result;
    }
}
