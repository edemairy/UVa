
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
        try {
            while (true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (IOException ex) {
        }
        System.out.print(result);
    }
    private static int[] sf = new int[11];
    private static int[] sr = new int[11];
    private static ArrayList<Double> d = new ArrayList<Double>();

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        int f = readInt(reader);
        int r = readInt(reader);
        d.clear();
        Arrays.fill(sf, 0);
        Arrays.fill(sr, 0);
        for (int i = 0; i < f; ++i) {
            sf[i] = readInt(reader);
        }
        for (int i = 0; i < r; ++i) {
            sr[i] = readInt(reader);
        }
        for (int cf = 0; cf < f; ++cf) {
            for (int cr = 0; cr < r; ++cr) {
                d.add(1.0 * sf[cf] / sr[cr]);
            }
        }
        Collections.sort(d);
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        double res = 0;
        for (int i = 0; i < d.size() - 1; ++i) {
            res = Math.max(res, d.get(i + 1) / d.get(i));
        }
        formatter.format(Locale.ENGLISH, "%.02f", res);
        result.append(formatter.out());
        //        for (int i = 0; i < 5; ++i) {
        //            formatter.format("%3d", n[i]);
        //        }
        //        result.append(formatter.out());
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

}
