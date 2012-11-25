
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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
//        nbTC = readInt(reader);
//        scanner.nextLine();
//        for (int tc = 1; tc <= nbTC; ++tc) {
        result.append(oneTestCase(reader));
//        result.append('\n');
//        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        Set<String> ignore = new HashSet<String>();
        ignoreLoop:
        while (true) {
            String current = reader.readLine();
            if (current.equals("::")) {
                break ignoreLoop;
            } else {
                ignore.add(current);
            }
        }

        Map<String, ArrayList<String>> dico = new TreeMap<String, ArrayList<String>>();
        try {
            while (true) {
                String current = reader.readLine();
                String[] parts = current.split(" +");
                for (int p = 0; p < parts.length; ++p) {
                    String currentWord = parts[p].toLowerCase();
                    if (!ignore.contains(currentWord)) {
                        if (!dico.containsKey(currentWord)) {
                            dico.put(currentWord, new ArrayList<String>());
                        }
                        StringBuilder newString = new StringBuilder();
                        for (int i = 0; i < parts.length; ++i) {
                            if (i != p) {
                                newString.append(parts[i].toLowerCase());
                            } else {
                                newString.append(parts[i].toUpperCase());
                            }
                            if (i != parts.length - 1) {
                                newString.append(" ");
                            }
                        }
                        dico.get(currentWord).add(newString.toString());
                    }
                }
            }
        } catch (Exception e) {
        }
        for (String key : dico.keySet()) {
            for (String value : dico.get(key)) {
                formatter.format("%s\n", value);
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
}
