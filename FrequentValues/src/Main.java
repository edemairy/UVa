
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;
import java.util.TreeMap;
import java.util.TreeSet;
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
        result.append(oneTestCase(reader));
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        mainloop:
        while (true) {
            n = readInt(reader);
            if (n == 0) {
                break mainloop;
            }
            int q = readInt(reader);
            values = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                values[i] = readInt(reader);
            }
            build();
            f[0] = 0;
            for (int i = 0; i < q; i++) {
                int min = readInt(reader);
                int max = readInt(reader);

                int result;
                if (values[min] == values[max]) {
                    result = max - min + 1;
                } else {
                    int left = e[min] - min + 1;
                    int right = max - s[max] + 1;
                    int middle = 0;
                    if (s[max] > e[min] + 1) {
                        middle = rmaxq(e[min]+1, s[max] - 1, 1, 1, n);
                    }
                    result = Math.max(left, Math.max(right, f[middle]));
                }
                formatter.format("%d\n", result);
            }

        }
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
    private static int[] s;
    private static int[] e;
    private static int[] f;
    private static int[] reduced;
    private static int split;
    private static int[] tree;
    private static int n;
    private static int[] values;

    private static void build() {
        s = new int[n + 1];
        e = new int[n + 1];
        f = new int[n + 1];
        int i = 1;
        while (i <= n) {
            int current = values[i];
            s[i] = i;
            int j = i + 1;
            while (j <= n && values[j] == current) {
                s[j] = i;
                j++;
            }
            for (int k = j - 1; k >= i; k--) {
                e[k] = j - 1;
                f[k] = j - i;
            }

            i = j;
        }
        tree = new int[(int) (2 * Math.pow(2., Math.log(n) / Math.log(2) + 1)) + 1];
        build_tree(tree, 1, 1, n);
    }

    public static void build_sqrt_split(int[] values) {
        int n = values.length;
        reduced = new int[(int) Math.ceil(Math.sqrt(n))];
        split = (int) Math.sqrt(n);
        for (int i = 0; i < reduced.length; i++) {
            int result = -1;
            int startSplit = i * split;
            int endSplit = Math.min(startSplit + split - 1, n - 1);
            for (int j = 0; j < split; j++) {
                int pos = i * split + j;
                if (pos < n) {
                    int nb = Math.min(e[pos], endSplit) - Math.max(s[pos], startSplit) + 1;
                    result = Math.max(nb, result);
                }
            }
            reduced[i] = result;
        }
    }

    public static int build_tree(int[] tree, int node, int l, int r) {
        if (l == r) {
            tree[node] = l;
        } else {
            int m = (l + r) / 2;
            int rl = build_tree(tree, 2 * node, l, m);
            int rr = build_tree(tree, 2 * node + 1, m + 1, r);
            if (f[rl] > f[rr]) {
                tree[node] = rl;
            } else {
                tree[node] = rr;
            }
//            if (trueVal(l,r,rl) > trueVal(l,r,rr)) {
//                tree[node] = rl;
//            } else {
//                tree[node] = rr;
//            }
        }
        return tree[node];
    }

    private static int rmaxq(int min, int max, int node, int l, int r) {
        if (r < min || max < l) {
            return -1;
        }
        if (l == r) {
            return tree[node];
        }
        if (min <= l && r <= max) {
            return tree[node];
        }
        int m = (l + r) / 2;
        int p1 = rmaxq(min, max, 2 * node, l, m);
        int p2 = rmaxq(min, max, 2 * node + 1, m + 1, r);
        if (p1 == -1) {
            return  p2;
        }
        if (p2 == -1) {
            return p1;
        }
        if (f[p1] > f[p2]) {
            return p1;
        } else {
            return p2;
        }
    }
}
