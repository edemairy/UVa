
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        nbTC = scanner.nextInt();
        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(tc).append(" ").append(oneTestCase(scanner));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static Pattern pattern = Pattern.compile("(\\d\\d)(\\d\\d)(\\d\\d\\d\\d)");

    private static StringBuilder oneTestCase(Scanner scanner) {
        String dateString = scanner.next();

        Matcher matcher = pattern.matcher(dateString);
        matcher.matches();
        int m = Integer.valueOf(matcher.group(1));
        int d = Integer.valueOf(matcher.group(2));
        int y = Integer.valueOf(matcher.group(3));
        long ds = date2d(d, m, y);
        Integer rd = new Integer(0);
        Integer rm = new Integer(0);
        Integer ry = new Integer(0);
        long birthDate = ds + 40 * 7;
        int[] resultDate = d2date(birthDate);
        rd = resultDate[0];
        rm = resultDate[1];
        ry = resultDate[2];
        
        String sign = null;
        /*
         * Aquarius	January, 21	February, 19 Pisces	February, 20	March, 20 Aries
         * March, 21	April, 20 Taurus	April, 21	May, 21 Gemini	May, 22 June, 21
         * Cancer	June, 22	July, 22 Leo July, 23	August, 21 Virgo	August, 22
         * September, 23 Libra	September, 24	October, 23 Scorpio	October, 24
         * November, 22 Sagittarius	November, 23	December, 22 Capricorn
         * December, 23	January, 20
         */
        if (birthDate < date2d(21, 1, ry)) {
            sign = "capricorn";
        } else if (birthDate < date2d(20, 2, ry)) {
            sign = "aquarius";
        } else if (birthDate < date2d(21, 3, ry)) {
            sign = "pisces";
        } else if (birthDate < date2d(21, 4, ry)) {
            sign = "aries";
        } else if (birthDate < date2d(22, 5, ry)) {
            sign = "taurus";
        } else if (birthDate < date2d(22, 6, ry)) {
            sign = "gemini";
        } else if (birthDate < date2d(23, 7, ry)) {
            sign = "cancer";
        } else if (birthDate < date2d(22, 8, ry)) {
            sign = "leo";
        } else if (birthDate < date2d(24, 9, ry)) {
            sign = "virgo";
        } else if (birthDate < date2d(24, 10, ry)) {
            sign = "libra";
        } else if (birthDate < date2d(23, 11, ry)) {
            sign = "scorpio";
        } else if (birthDate < date2d(23, 12, ry)) {
            sign = "sagittarius";
        } else if (birthDate <= date2d(31, 12, ry)) {
            sign = "capricorn";
        }

        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        formatter.format("%02d/%02d/%04d %s", rm, rd, ry,sign);
        result.append(formatter.out());
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }
    private static int[] durationM = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static long date2d(final int d, final int m, final int y) {
        long r = 0;
        for (int cy = 0; cy < y; ++cy) {
            if (isBis(cy)) {
                r += 366;
            } else {
                r += 365;
            }
        }
        for (int cm = 1; cm < m; ++cm) {
            r += durationM[cm];
        }
        if (isBis(y) && (m > 2)) {
            r += 1;
        }
        r += d;
        return r;
    }

    private static int[] d2date(long l) {
        int ry, rm, rd;
        ry = 0;
        fory:
        do {
            if (l <= lengthY(ry)) {
                break fory;
            }
            l -= lengthY(ry);
            ++ry;
        } while (true);
        rm = 1;
        form:
        do {
            int durM = durationM[rm] + (((rm == 2) && (isBis(ry))) ? 1 : 0);
            if (l <= durM) {
                break form;
            }
            l -= durM;
            ++rm;
        } while (true);
        rd = (int) l;
        int[] result = new int[3];
        result[0] = rd;
        result[1] = rm;
        result[2] = ry;
        return result;
    }

    private static int lengthY(int cy) {
        return ((isBis(cy)) ? 366 : 365);
    }

    private static boolean isBis(int cy) {
        if (cy % 4 == 0) {
            if (cy % 100 == 0) {
                if (cy % 400 == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
