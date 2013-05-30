
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
        while (true) {
            String currentLine = reader.readLine();
            if (currentLine == null || currentLine.isEmpty()) {
                break mainloop;
            } else {
                result.append(oneTestCase(currentLine));
                result.append('\n');
            }
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(String currentLine) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        ArrayList<Integer> unordered = new ArrayList<Integer>();

        String[] parts = currentLine.split(" +");
        for (int p = 0; p < parts.length; ++p) {
            unordered.add(Integer.parseInt(parts[p]));
            formatter.format("%s", parts[p]);
            if (p != parts.length - 1) {
                formatter.format(" ");
            }
        }

        formatter.format("\n");
        ArrayList<Integer> ordered = new ArrayList<Integer>(unordered);
        Collections.sort(ordered);
        ArrayList<Integer> result = new ArrayList<Integer>();
        int sz = unordered.size();
        for (int pos = unordered.size() - 1; pos > 0; --pos) {
            if (ordered.get(pos) == unordered.get(pos)) {
                continue;
            } else {
                if (unordered.get(0) == ordered.get(pos)) {
                    result.add(convert(pos, sz));
                    shuffle(unordered, pos);
                } else {
                    int p2;
                    findp2:
                    for (p2 = pos - 1; p2 >= 0; --p2) {
                        if (ordered.get(pos) == unordered.get(p2)) {
                            break findp2;
                        }
                    }
                    result.add(convert(p2, sz));
                    result.add(convert(pos, sz));
                    shuffle(unordered, p2);
                    shuffle(unordered, pos);
                }
            }
        }
        result.add(0);

        for (int r = 0; r < result.size(); ++r) {
            formatter.format("%d", result.get(r));
            if (r != result.size() - 1) {
                formatter.format(" ");
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

    private static void shuffle(ArrayList<Integer> result, int pos) {
        for (int i = 0; i < (pos + 1) / 2; ++i) {
            Collections.swap(result, i, pos - i);
        }
    }

    private static Integer convert(int p, int sz) {
        return sz - p;
    }
}
