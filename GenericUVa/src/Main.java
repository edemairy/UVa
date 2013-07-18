
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

    private int nbTC;
    private StringBuilder result = new StringBuilder();

    private static class EndException extends RuntimeException {
    }

    public void run() throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
        try {
            for (int tc = 1; tc <= nbTC; ++tc) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (EndException e) {
        }
        System.out.print(result);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        output.append(formatter.out());
        return output;
    }

    private int readInt(BufferedReader reader) throws IOException {
        int r = 0;
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
            r = r * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return r;
        } else {
            return -r;
        }
    }

    private char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
