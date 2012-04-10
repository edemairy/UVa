
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
//        scanner.nextLine();
        try {
            while (true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (IOException ex) {
        }
        System.out.print(result);
    }
    private static int[] a = new int[20];
    private static int[] b = new int[20];
    private static int[] c = new int[20];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        int n = readInt(reader);
        int m = readInt(reader);
        if (n + m == 0) {
            throw new IOException("end");
        }
        for (int i = 0; i < m; ++i) {
            a[i] = readInt(reader);
            b[i] = readInt(reader);
            c[i] = readInt(reader);
        }
        int r = 0;
        Integer[] pos = new Integer[n];
        for (int i = 0; i < n; ++i) {
            pos[i] = i;
        }
        loopp:
        do {
            for (int i = 0; i < m; ++i) {
                List<Integer> posList = Arrays.asList(pos);
                int pa = posList.indexOf(a[i]);
                int pb = posList.indexOf(b[i]);
                int d = Math.abs(pa - pb);
                if ((c[i] < 0) && (d < -c[i])) {
                    continue loopp;
                }
                if ((c[i] > 0) && (d > c[i])) {
                    continue loopp;
                }
            }
            ++r;
        } while (next(pos));
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        result.append(r);
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

    private static boolean next(Integer[] word) {
        /*
         * Find the largest index k such that a[k] < a[k + 1]. If no such index
         * exists, the permutation is the last permutation. Find the largest
         * index l such that a[k] < a[l]. Since k + 1 is such an index, l is
         * well defined and satisfies k < l. Swap a[k] with a[l]. Reverse the
         * sequence from a[k + 1] up to and including the final element a[n].
         */
        boolean foundk = false;
        int k;
        searchk:
        for (k = word.length - 2; k >= 0; --k) {
            if (word[k] < word[k + 1]) {
                foundk = true;
                break searchk;
            }
        }
        if (!foundk) {
            return false;
        }
        int l;
        searchl:
        for (l = word.length - 1; l > k; --l) {
            if (word[k] < word[l]) {
                break searchl;
            }
        }
        int t = word[l];
        word[l] = word[k];
        word[k] = t;
        Integer[] temp = Arrays.copyOfRange(word, k + 1, word.length);
        for (int i = 0; i < word.length - k - 1; ++i) {
            word[k + 1 + i] = temp[temp.length - i - 1];
        }
        return true;
    }
}
