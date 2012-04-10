
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
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = 1;
        N = readInt(reader);
//        scanner.nextLine();
        while (N != 0) {
            result.append("**********************************************************\nNetwork #" + nbTC + "\n");
            result.append(oneTestCase(reader));            
            N = readInt(reader);
            ++nbTC;
        }
        System.out.print(result);
    }
    private static int N;
    private static int[] x = new int[10];
    private static int[] y = new int[10];
    private static double[][] bd = new double[10][1 << 9];
    private static int[][] min = new int[10][1 << 9];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            x[i] = readInt(reader);
            y[i] = readInt(reader);
        }

        for (int i=0; i<10; ++i) {
            Arrays.fill(min[i],-1);
            Arrays.fill(bd[i],-1);            
        }
        
        int best = -1;
        double bestValue = Double.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            double curValue = bestValue(i, 1 << i);
            if (curValue<bestValue) {
                bestValue = curValue;
                best = i;
            }
        }
        int mask = 0;
        int current = best;
        mask |= (1<<current);
        int next = min[current][mask];
        Formatter format = new Formatter(Locale.ENGLISH);
        double total = 0;
        while (next!=-1) {
            format.format("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.\n", new Object[]{x[current], y[current], x[next], y[next], 16+dist(current, next)});
            total += 16+dist(current, next);
            current = next;
            mask |= (1<<current);        
            next = min[current][mask];
        }
        format.format("Number of feet of cable required is %.2f.\n", total);
        result.append(format.out());
        
        return result;
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

    private static double bestValue(int i, int mask) {
        double result;
        if (bd[i][mask] < 0) {
            if (mask == ((1 << N) - 1)) {
                result = 0;
            } else {
                double bv = Double.MAX_VALUE;
                double cv;
                int bj = -1;
                
                for (int j = 0; j < N; ++j) {
                    if (((1 << j) & mask) == 0) {
                        cv = dist(i,j)+bestValue(j, mask | (1<<j) );
                        if (cv < bv) {
                            bv = cv;
                            bj = j;
                        }
                    }                    
                }
                result = bv;
                min[i][mask] = bj;
            }
            bd[i][mask] = result;
        }
        return bd[i][mask];
    }

    private static double dist(int i, int j) {
        return Math.sqrt((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j]));
    }
}
