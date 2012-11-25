
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
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
    private static boolean end = false;
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        nbTC = readInt(reader);
//        scanner.nextLine();
        do {
            result.append(oneTestCase(reader));
            if (!end) {result.append('\n');}
        } while (!end);
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader)  throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        int n1 = readInt(reader);
        int n2 = readInt(reader);
        if ( (n1==0) && (n2==0)) {
            end = true;
            return output;
        }
        int nbc = 0;
        int c = 0;
        while ( (n1!=0) || (n2!=0)) {
            c = ((n1%10) + (n2%10) + c)/10;
            nbc+=c;
            n1 /= 10;
            n2 /= 10;
        }
        if (nbc==0) {
            formatter.format("No carry operation.");
        } else if (nbc==1) {
            formatter.format("1 carry operation.");
        } else if (nbc > 1) {
            formatter.format("%d carry operations.",nbc);
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
}
