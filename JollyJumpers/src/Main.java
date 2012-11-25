
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

   private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        scanner.nextLine();
        try {
            while (true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (IOException e) {
        }
        System.out.print(result);
    }
    private static int[] number = new int[3001];
    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        
        boolean jj = true;
        int N = readInt(reader);
        
        
        TreeSet<Integer> diffs = new TreeSet<Integer>();
        fori:
        for (int i = 0; i < N; ++i) {
            number[i] = readInt(reader);
            int diff = 0;
            if (i > 0) {
                diff = Math.abs(number[i] - number[i - 1]);

                if ((diff > N - 1)||(diff==0)) {
                    jj = false;
                }
                diffs.add(diff);
            }
        }
        jj = (jj) && (diffs.size() == (N - 1));
        if (jj) {
            output.append("Jolly");
        } else {
            output.append("Not jolly");
        }
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
}
