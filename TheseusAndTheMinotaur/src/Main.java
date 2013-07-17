
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
//        nbTC = readInt(reader);
//        scanner.nextLine();
        try {
//            for (int tc = 1; tc <= nbTC; ++tc) {
            while (true == true) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
//            }
        } catch (EndException e) {
        }
        System.out.print(result);
    }
    private static TreeMap<Character, Boolean> light = new TreeMap<Character, Boolean>();

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException, EndException {
        light.clear();
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        String currentLine = reader.readLine();
        if (currentLine.contains("#")) {
            throw new EndException();
        }
        TreeMap<Character, ArrayList<Character>> graph = new TreeMap<Character, ArrayList<Character>>();
        String[] parts = currentLine.split(" ");
        fillGraph(parts[0], graph);
        for (char k : graph.keySet()) {
            light.put(k, Boolean.FALSE);
        }
        Character posMinotaur = parts[1].charAt(0);
        Character posThesee = parts[2].charAt(0);
        long k = Long.parseLong(parts[3]);
        TreeSet<Character> alreadySeen = new TreeSet<Character>();
        mainloop:
        while (1 > 0) {
            alreadySeen.clear();
            for (long i = 0; i < k; i++) {

                ArrayList<Character> next = graph.get(posMinotaur);
                boolean nextFound = false;
                for (char c : next) {
                    if (light.get(c) || posThesee == c) {
                        continue;
                    } else {
                        posThesee = posMinotaur;
                        posMinotaur = c;
//                        if (alreadySeen.contains(c)) {
//                            i += alreadySeen.size() * ((k - i - 1) / alreadySeen.size());
//                        }
//                        alreadySeen.add(c);
                        nextFound = true;
                        break;
                    }
                }
                if (!nextFound) {
                    formatter.format("/%c", posMinotaur);
                    break mainloop;
                }
            }
            light.put(posThesee, Boolean.TRUE);
            formatter.format("%c ", posThesee);
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

    private static void fillGraph(String string, TreeMap<Character, ArrayList<Character>> graph) {
        String[] parts = string.split(";");
        for (String part : parts) {
            String[] vertices = part.split(":");
            char s = vertices[0].charAt(0);
            addVertice(s, graph);
            for (char c : vertices[1].toCharArray()) {
                addVertice(c, graph);
                addEdge(s, c, graph);
            }
        }
    }

    private static void addVertice(char charAt, TreeMap<Character, ArrayList<Character>> graph) {
        if (!graph.containsKey(charAt)) {
            graph.put(charAt, new ArrayList<Character>());
        }
    }

    private static void addEdge(char s, char c, TreeMap<Character, ArrayList<Character>> graph) {
        if (c != '.' && s!=c) {
            graph.get(s).add(c);
        }
    }

    private static class EndException extends Exception {

        public EndException() {
        }
    }
}
