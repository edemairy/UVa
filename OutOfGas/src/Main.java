
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
        Scanner reader = new Scanner(System.in);
        reader.useLocale(Locale.ENGLISH);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = reader.nextInt();
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append("Case #");
            result.append(tc);
            result.append(":\n");

            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static double D;
    private static int N;
    private static int A;
    private static double[] t = new double[2001];
    private static double[] x = new double[2001];
//    private static double[] a = new double[251];
    private static double a;

    private static StringBuilder oneTestCase(Scanner reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        D = reader.nextDouble();
        N = reader.nextInt();
        A = reader.nextInt();

        t[0] = 0;
        x[0] = 0;
        for (int i = 0; i < N; ++i) {
            t[i] = reader.nextDouble();
            x[i] = reader.nextDouble();
        }
        for (int i = 0; i < A; ++i) {
            a = reader.nextDouble();
            double res = 0;
            double pos = 0;
            double v = 0;
            forj:
            for (int j = 1; j < N; ++j) {
                double dt = t[j] - t[j - 1];
                double newPos = (.5 * a * dt + v) * dt + pos;
                if (newPos <= x[j]) {
                    if (newPos >= D) {
                        res = t[j - 1] + pol2(.5 * a, v, pos - D);
                        pos = D;
                        break forj;
                    }
                    v += a * dt;
                    pos = newPos;
                    res += dt;
                } else {
                    double v2 = (x[j] - x[j - 1]) / (t[j] - t[j - 1]);


                    if (newPos >= D) {
                        double ttr = pol2(.5 * a, v - v2, pos - x[j - 1]);
                        if (x[j - 1] + v2 * ttr <= D) {

                            if (x[j] >= D) {
                                pos = D;
                                res = t[j - 1] + (D - x[j - 1]) / v2;
                                break forj;
                            } else {
                                pos = x[j];
                                v = v2;
                            }
                        } else {
                            res = t[j - 1] + pol2(.5 * a, v, pos - D);
                            pos = D;
                            break forj;
                        }

                    } else {
                        pos = x[j];
                        v = v2;
                    }
                }
            }
            if ((pos < D) && (Math.abs(pos - D) > 1e-9)) {
                res = t[N - 1] + pol2( a, 2*v, 2*(pos - D));
            }
            formatter.format("%.7f", res);
            if (i < (A - 1)) {
                formatter.format("\n");
            }
        }



        //        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        output.append(formatter.out());
        return output;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        boolean positive = true;
        char currentChar = (char) reader.read();

        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        if (currentChar == (char) -1) {
            throw new IOException("end of stream");
        }
        if (currentChar == '-') {
            positive = false;
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return result;
        } else {
            return -result;
        }
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }

    private static double pol2(double a, double b, double c) {
        BigDecimal ba = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal bc = new BigDecimal(c);
        BigDecimal delta = (bb.multiply(bb)).subtract(bc.multiply(ba).multiply(new BigDecimal(4)));
        return (1 / (2 * a)) * (-b + Math.sqrt(delta.doubleValue()));
    }
}
