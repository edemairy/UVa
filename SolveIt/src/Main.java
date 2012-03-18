
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (IOException ex) {
        }
        System.out.print(result);
    }
    private static int p, q, r, s, t, u;

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        p = readInt(reader);
        q = readInt(reader);
        r = readInt(reader);
        s = readInt(reader);
        t = readInt(reader);
        u = readInt(reader);

        double xl = 0;
        double fxl = f(xl);
        double xu = 1;
        double fxu = f(xu);
        if (Math.abs(fxl) < 1e-6) {
            formatter.format(Locale.ENGLISH, "%.04f", xl);
            result.append(formatter);
            return result;
        }
        if (Math.abs(fxu) < 1e-6) {
            formatter.format(Locale.ENGLISH, "%.04f", xu);
            result.append(formatter);
            return result;
        }
        if (fxl * fxu > 1e-6) {
            result.append("No solution");
            return result;
        }
        while (Math.abs(xl - xu) > 1e-8) {
            double xm = (xl + xu) / 2;
            double fxm = f(xm);
            if (fxm * fxu < 1e-6) {
                xl = xm;
            } else {
                xu = xm;
            }
        }
        formatter.format(Locale.ENGLISH, "%.04f", xl);
        result.append(formatter);
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
    }

    public static double f(double x) {
        return p * Math.exp(-x) + q * Math.sin(x) + r * Math.cos(x) + s * Math.tan(x) + t * x * x + u;
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
}
