
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        mainloop:
        do {
            String currentLine = reader.readLine();
            if (currentLine.equals("0")) {
                break mainloop;
            }
            result.append(oneTestCase(currentLine));
        } while (true);
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(String currentLine) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        String[] parts = currentLine.split(" +");
        ArrayList<Integer> ordered = new ArrayList<Integer>();
        for (int p =1; p<parts.length; ++p) {
            ordered.add(Integer.parseInt(parts[p]));
        }
        boolean possible = true;
        fori:
        for (int i = 0; i < ordered.size(); ++i) {
            Collections.sort(ordered);
            int v = ordered.get(ordered.size()-1);
            ordered.set(ordered.size()-1, 0);
            
            for (int pos = ordered.size()-2; v > 0; --pos) {
                if (pos<0) {
                    possible = false;
                    break fori;
                } else {
                    if (ordered.get(pos)>0) {
                        v--;
                        ordered.set(pos, ordered.get(pos)-1);
                    }
                }
            }
        }
        if (possible) {
            formatter.format("Possible\n");
        } else {
            formatter.format("Not possible\n");
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

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
