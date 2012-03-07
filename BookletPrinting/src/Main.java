
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


        int n = scanner.nextInt();
        while (n != 0) {
            result.append(oneTestCase(n));

            n = scanner.nextInt();
//            if (n != 0) {
                result.append('\n');
//            }
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(int n) {
        Formatter formatter = new Formatter();
        int nbLeafs = (int) Math.ceil(n / 4.);
        int[][] leafs = new int[nbLeafs + 1][4];
        for (int l = 1; l <= nbLeafs; ++l) {
            Arrays.fill(leafs[l], 0);
        }
        for (int p = 1; p <= Math.min(n, 2 * nbLeafs); ++p) {
            leafs[(int) Math.ceil(p / 2.)][ (p % 2 == 1) ? 1 : 2] = p;
        }
        for (int p = 2 * nbLeafs + 1; p <= Math.min(n, 4 * nbLeafs); ++p) {
            leafs[2 * nbLeafs - ((int) Math.ceil(p / 2.) - 1)][ (p % 2 == 1) ? 3 : 0] = p;
        }
        StringBuilder result = new StringBuilder();
        result.append("Printing order for ").append(n).append(" pages:");
        for (int l = 1; l <= nbLeafs; l++) {
            result.append("\nSheet ").append(l).append(", front: ").append(val(leafs[l][0])).append(", ").append(val(leafs[l][1]));
            if ((!val(leafs[l][2]).equals("Blank")) || (!val(leafs[l][3]).equals("Blank"))) {
                result.append("\nSheet ").append(l).append(", back : ").append(val(leafs[l][2])).append(", ").append(val(leafs[l][3]));
            }
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }

    private static String val(int v) {
        return (v == 0) ? "Blank" : Integer.toString(v);
    }
}
