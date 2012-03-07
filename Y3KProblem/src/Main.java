
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private static int MAXY = 3000000;
    private static boolean[] leap = new boolean[MAXY];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));

        initLeap();
        int delta = scanner.nextInt();
        int d = scanner.nextInt();
        int m = scanner.nextInt();
        int y = scanner.nextInt();
        while ((delta != 0) || (d != 0) || (m != 0) || (y != 0)) {
            String result = oneTestCase(delta, d, m, y);
            System.out.println(result);
            delta = scanner.nextInt();
            d = scanner.nextInt();
            m = scanner.nextInt();
            y = scanner.nextInt();
        }
    }

    private static String oneTestCase(int delta, int d, int m, int y) {
        String result = l2d(d2l(d, m, y) + delta);
        return result;
    }
    /*
     * Remember that in the standard Gregorian calendar we use there are 365
     * days in a year, except for leap years in which there are 366. Leap years
     * are all years divisible by 4 and not divisible by 100, except for those
     * divisible by 400. Thus 1900 was not a leap year, 1904, 1908 ... 1996 were
     *
     */

    private static void initLeap() {
        for (int y = 0; y < MAXY; ++y) {
            if (y % 4 == 0) {
                if (y % 100 != 0) {
                    leap[y] = true;
                } else {
                    if (y % 400 == 0) {
                        leap[y] = true;
                    } else {
                        leap[y] = false;
                    }
                }
            } else {
                leap[y] = false;
            }
        }
        assert !leap[1900];
        assert leap[1904];
        assert leap[1908];
        assert leap[1996];

    }
    /*
     * Remember that in the standard Gregorian calendar we use there are 365
     * days in a year, except for leap years in which there are 366. Leap years
     * are all years divisible by 4 and not divisible by 100, except for those
     * divisible by 400. Thus 1900 was not a leap year, 1904, 1908 ... 1996 were
     * leap years, 2000 will be a leap year, 2100 will not be a leap year, etc.
     * The number of days in each month in a normal year is 31, 28, 31, 30, 31,
     * 30, 31, 31, 30, 31, 30, 31; in a leap year, the second month has 29 days.
     */

    static private long d2l(int rd, int rm, int ry) {
        long r = 0;
        for (int y = 1998; y < ry; ++y) {
            r += (leap[y]) ? 366 : 365;
        }
        for (int m = 1; m < rm; ++m) {
            switch (m) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    r += 31;
                    continue;
                case 4:
                case 6:
                case 9:
                case 11:
                    r += 30;
                    continue;
                case 2:
                    r += (leap[ry]) ? 29 : 28;
            }
        }
        r += rd;
        return r;
    }

    static private String l2d(long d) {
        int ry = 0;
        int rm = 0;
        int rd = 0;
        fory:
        for (int y = 1998; y < MAXY; ++y) {
            int ly = (leap[y]) ? 366 : 365;
            if (d <= ly) {
                ry = y;
                break fory;
            } else {
                d -= ly;
            }
        }
        form:
        for (int m = 1; m <= 12; ++m) {
            int lm = -1;
            switch (m) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    lm = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    lm = 30;
                    break;
                case 2:
                    lm = (leap[ry]) ? 29 : 28;
            }
            if (d > lm) {
                d -= lm;
            } else {
                rm = m;
                break form;
            }
        }
        rd = (int) d;
        String result = rd + " " + rm + " " + ry;
        return result;
    }
}
