
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
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
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);


        fortc:
        while (true) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if ((a == 0) && (b == 0) && (c == 0)) {
                break fortc;
            }
            result.append(oneTestCase(scanner, a, b, c));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static int maxT = 5 * 60 * 60;
    public static boolean[] lights = new boolean[20000];

    private static StringBuilder oneTestCase(Scanner scanner, int a, int b, int c) {
        Arrays.fill(lights, true);
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        ArrayList<Integer> freqs = new ArrayList<Integer>();
        freqs.add(a);
        freqs.add(b);
        int lastFreq = c;
        while (lastFreq != 0) {
            freqs.add(lastFreq);
            lastFreq = scanner.nextInt();
        }
        for (int freq : freqs) {
            for (int t = 0; t <= maxT; ++t) {
                lights[t] &= (t % (2 * freq) < (freq - 5));
            }
        }
        int t = 0;
        while ((lights[t]) && (t <= maxT)) {
            ++t;
        }
        if (t > maxT) {
            result.append("Signals fail to synchronise in 5 hours");
            return result;
        }
        while ((!lights[t]) && (t <= maxT)) {
            ++t;
        }
        if (t > maxT) {
            result.append("Signals fail to synchronise in 5 hours");
            return result;
        }

        int h = t / 3600;
        int m = (t % 3600) / 60;
        int s = (t % 60);
        formatter.format("%02d:%02d:%02d", h, m, s);
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        result.append(formatter.out());
        return result;
    }
}
