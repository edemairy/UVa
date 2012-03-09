
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
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> data = new ArrayList<Integer>();
        try {
            while (true) {
                data.add(readInt(reader));
            }
        } catch (IOException e) {
        }
        result.append(oneTestCase(data));
//        result.append('\n');
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(ArrayList<Integer> data) {
        ArrayList<Integer> lis = new ArrayList<Integer>();
        int[] length = new int[data.size()];
        Arrays.fill(length, 1);
        TreeMap<Integer, Integer> pred = new TreeMap<Integer, Integer>();


        for (int i = 0; i < data.size(); ++i) {
            int ip = Collections.binarySearch(lis, data.get(i));
            if (ip < 0) {
                ip = -(ip + 1);
                if (ip == lis.size()) {
                    lis.add(data.get(i));
                } else {
                    lis.set(ip, data.get(i));
                }
                pred.put(data.get(i), ((ip == 0) ? -1 : lis.get(ip - 1)));
                length[i] = ip + 1;
            }
        }


        StringBuilder result = new StringBuilder();
        int posM = 0;
        int bestl = 0;
        for (int i = length.length - 1; i >= 0; --i) {
            if (length[i] > bestl) {
                posM = i;
                bestl = length[i];
            }
        }
        result.append(bestl).append('\n').append('-').append('\n');
        Stack<Integer> rStack = new Stack<Integer>();
        int elt = data.get(posM);
        whilet:
        while (true) {
            rStack.push(elt);
            if (pred.get(elt) == -1) {
                break whilet;
            }
            elt = pred.get(elt);
        }
        while (!rStack.isEmpty()) {
            result.append(rStack.pop()).append('\n');
        }

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
