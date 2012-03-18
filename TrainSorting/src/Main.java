
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        nbTC = readInt(reader);
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static int[] cars = new int[2001];
    private static int[] lis = new int[2001];
    private static int[] iLis = new int[2001];
    private static int[] lMaxLis = new int[2001];
    private static int[] lMaxLds = new int[2001];
    private static Integer[] lds = new Integer[2001];
    private static Integer[] iLds = new Integer[2001];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Arrays.fill(lis, 0);
        Arrays.fill(lds, 0);
        Arrays.fill(iLds, 0);
        Arrays.fill(iLis, 0);
        Arrays.fill(lMaxLds, 0);
        Arrays.fill(lMaxLis, 0);
        Arrays.fill(cars, -1);

        int nbCars = readInt(reader);
        for (int c = 0; c < nbCars; ++c) {
            cars[c] = readInt(reader);
        }

        HashMap<Integer, Integer> predLis = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> predLds = new HashMap<Integer, Integer>();

        int lisMax = 0;
        int ldsMax = 0;
        for (int c = nbCars - 1; c >= 0; --c) {
            int idxLis = Arrays.binarySearch(lis, 0, lisMax, cars[c]);
            if (idxLis < 0) {
                idxLis = -(idxLis + 1);
                lis[idxLis] = cars[c];
                iLis[idxLis] = c;
                if (idxLis == 0) {
                    predLis.put(c, -1);
                } else {
                    predLis.put(c, iLis[idxLis - 1]);
                }
                lisMax = Math.max(lisMax, idxLis + 1);
                lMaxLis[c] = idxLis + 1;
            }
            int idxLds = Arrays.binarySearch(lds, 0, ldsMax, cars[c], new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            if (idxLds < 0) {
                idxLds = -(idxLds + 1);
                lds[idxLds] = cars[c];
                iLds[idxLds] = c;
                if (idxLds == 0) {
                    predLds.put(c, -1);
                } else {
                    predLds.put(c, iLds[idxLds - 1]);
                }
                ldsMax = Math.max(ldsMax, idxLds + 1);
                lMaxLds[c] = idxLds + 1;
            }
        }
        int r = 0;
        for (int c = 0; c < nbCars; ++c) {
            r = Math.max(r, lMaxLds[c] + lMaxLis[c] - 1);
        }

        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());

        result.append(r);
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        char currentChar = (char) reader.read();
        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        return result;
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
