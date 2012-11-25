
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        mainloop: while (true) {
            int nbLines = readInt(reader);
            if (nbLines != 0) {
                result.append(oneTestCase(reader, nbLines));
                result.append('\n');
            } else {
                break mainloop;
            }
        }  
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader, int nbLines) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        int[] width = new int[nbLines];
        int sum = 0;
        for (int i = 0; i < nbLines; i++) {
            String current = reader.readLine();
            int w =  0;
            for (int c = 0; c < 25; c++) {
                if (current.charAt(c)==' ') {
                    w++;
                }
            }
            width[i] = w;
            sum += w;
        }
        
        Arrays.sort(width);
        int min = width[0];
        output.append(sum - min*nbLines);
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
