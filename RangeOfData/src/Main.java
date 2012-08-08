
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

/**
 *
 * @author edemairy
 */
public class Main {

    private static int nbTC;
    private static StringBuilder result = new StringBuilder();
    /**
     * @param args the command line arguments
     */
    private static int tc;

    public static void main(String[] args) throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 65000);
        nbTC = readInt(reader);
        for (tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append("\n");
        }
        System.out.print(result);
        System.out.flush();
    }
    public static StringBuilder output = new StringBuilder();
    private static int N;
    private static int[] val = new int[1000001];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        output.setLength(0);
        N = readInt(reader);
        int M = readInt(reader);
        Arrays.fill(val, 0);

        for (int i = 0; i < M; ++i) {
            int w = readInt(reader);
            int x = readInt(reader);
            int y = readInt(reader);
            int z = readInt(reader);
            if (z == 0) {
                continue;
            }
            if (w == 2) {
                z = -z;
            }

            if (y < N) {
                val[y + 1] += -z;
            }
            val[x] += z;

        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int cv = 0;
        for (int i = 0; i <= N; ++i) {
            if (i > N) {
                continue;
            }
            if (i < 1) {
                continue;
            }

            cv += val[i];
            int t = cv + i;
            min = Math.min(min, t);
            max = Math.max(max, t);
        }
        output.append(max - min);
        return output;
    }

    private static int readInt(Reader reader) throws IOException {
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

    private static char readChar(Reader reader) throws IOException {
        return (char) reader.read();
    }
}
