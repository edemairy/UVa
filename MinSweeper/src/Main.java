
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
        int l = scanner.nextInt();
        int c = scanner.nextInt();
        int nbT = 0;
        while ((l != 0) && (c != 0)) {
            ++nbT;
            scanner.nextLine();
            System.out.println("Field #" + nbT+":");
            oneCase(scanner, l, c);
            l = scanner.nextInt();
            c = scanner.nextInt();
            if ((l != 0) && (c != 0)) {
                System.out.println();
            }

        }


    }

    private static void oneCase(Scanner scanner, int nLines, int nCols) {
        int[][] r = new int[102][102];
        for (int i = 0; i < 102; ++i) {
            Arrays.fill(r[i], 0);
        }
        ArrayList<String> grid = new ArrayList<String>();
        for (int i = 0; i < nLines; ++i) {
            grid.add(scanner.nextLine());
        }
        for (int l = 1; l <= nLines; ++l) {
            for (int c = 1; c <= nCols; ++c) {
                if (grid.get(l - 1).charAt(c - 1) == '*') {
                    ++r[l - 1][c - 1];
                    ++r[l - 1][c];
                    ++r[l - 1][c + 1];
                    ++r[l][c - 1];
                    r[l][c] = 100;;
                    ++r[l][c + 1];
                    ++r[l + 1][c - 1];
                    ++r[l + 1][c];
                    ++r[l + 1][c + 1];
                }
            }
        }
        for (int l = 1; l <= nLines; ++l) {
            for (int c = 1; c <= nCols; ++c) {
                if (r[l][c] >= 100) {
                    System.out.print('*');
                } else {
                    System.out.print(r[l][c]);
                }
            }
            System.out.println();
        }
    }
}
