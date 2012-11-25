
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
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
    private static int N = 500000000;
//    private static BitSet ugly = new BitSet(N);
    private static BitSet isPrime = new BitSet(N);

    public static void main(String[] args) throws IOException {

        TreeSet<Long> numbers = new TreeSet<Long>();
        numbers.add(1L);
        int n = 1500;
        for (int i = 1; i < n; i++) {
            Long current = numbers.first();
            //System.out.print("The " + i + "th u'gly number is " + current + "\n");
            numbers.remove(current);
            numbers.add(current * 2);
            numbers.add(current * 3);
            numbers.add(current * 5);
        }
        System.out.print("The 1500'th ugly number is " + numbers.first()+".\n");
    }
}