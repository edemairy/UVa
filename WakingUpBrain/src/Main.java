
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
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
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (Exception e) {
        }
        System.out.print(result);
    }
    private static TreeMap<Character, TreeSet<Character>> neighbours = new TreeMap<Character, TreeSet<Character>>();

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        neighbours.clear();
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        int n = readInt(reader);
        int m = readInt(reader);
        String awakeLine = reader.readLine();
        for (int i = 0; i < m; i++) {
            String currentLine = reader.readLine();
            char s = currentLine.charAt(0);
            char e = currentLine.charAt(1);
            if (neighbours.get(s) == null) {
                neighbours.put(s, new TreeSet<Character>());
            }
            if (neighbours.get(e) == null) {
                neighbours.put(e, new TreeSet<Character>());
            }
            neighbours.get(s).add(e);
            neighbours.get(e).add(s);

        }

        TreeSet<Character> awake = new TreeSet<Character>();
        TreeSet<Character> sleeping = new TreeSet<Character>();
        for (int i = 0; i < 3; i++) {
            awake.add(awakeLine.charAt(i));
        }
        sleeping.addAll(neighbours.keySet());
        sleeping.removeAll(awake);

        TreeSet<Character> toAwake = new TreeSet<Character>();
        boolean goingFW = true;
        int nbSteps = 0;
        while (!sleeping.isEmpty() && goingFW) {
            nbSteps++;
            goingFW = false;
            toAwake.clear();
            for (Character c : sleeping) {
                int nbawake = 0;
                loopNeighbour:
                for (Character neighbour : neighbours.get(c)) {
                    if (awake.contains(neighbour)) {
                        nbawake++;
                    }
                    if (nbawake >= 3) {
                        toAwake.add(c);
                        goingFW = true;
                        break loopNeighbour;
                    }
                }
            }
            awake.addAll(toAwake);
            sleeping.removeAll(toAwake);
        }

        if (awake.size() == n) {
            formatter.format("WAKE UP IN, %d, YEARS", nbSteps);
        } else {
            formatter.format("THIS BRAIN NEVER WAKES UP");
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
