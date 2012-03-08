
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
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> data = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            data.add(scanner.nextInt());
        }
        result.append(oneTestCase(data));
//        result.append('\n');
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(ArrayList<Integer> data) {
        int[] length = new int[data.size()];
        Arrays.fill(length, 1);
        int[] pred = new int[data.size()];
        for (int i = 0; i < data.size(); ++i) {
            pred[i] = i;
        }

        for (int i = 0; i < data.size(); ++i) {
            int bestj = -1;
            int bestl = 0;
            for (int j = 0; j < i; ++j) {
                if (data.get(j) < data.get(i)) {
                    if (length[j] > bestl) {
                        bestj = j;
                        bestl = length[j];
                    }
                }
            }
            if (bestj == -1) {
                length[i] = 1;
                pred[i] = -1;
            } else {
                length[i] = bestl + 1;
                pred[i] = bestj;
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
        whilet:
        while (true) {
            rStack.push(data.get(posM));
            if (pred[posM] == -1) {
                break whilet;
            }
            posM = pred[posM];
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
}
