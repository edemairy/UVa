
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {
    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     *
     * @author edemairy
     */
    static public class UnionSet {

        protected TreeMap<String, String> pset = new TreeMap<String, String>();
        protected TreeMap<String, Integer> size = new TreeMap<String, Integer>();

        public UnionSet() {
        }

        public void init(String newName) {
            if (pset.get(newName) == null) {
                pset.put(newName, newName);
                size.put(newName, 1);
            }
        }

        String findSet(String name) {
            String current = name;
            while (current.compareTo(pset.get(current)) != 0) {
                current = pset.get(current);
            }
            pset.put(name, current);
            return current;
        }

        boolean isSameSet(String i, String j) {
            return findSet(i) == findSet(j);
        }

        int getSize(String i) {
            return size.get(findSet(i));
        }

        void unionSet(String i, String j) {
            String fsi = findSet(i);
            String fsj = findSet(j);
            pset.put(fsi, fsj);
            if (fsi.compareTo(fsj) != 0) {
                size.put(fsj, size.get(fsi) + size.get(fsj));
            }
        }
    }
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
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        UnionSet us = new UnionSet();

        int n = readInt(reader);
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            us.init(parts[0]);
            us.init(parts[1]);
            us.unionSet(parts[0], parts[1]); 
            formatter.format("%s\n", us.getSize(parts[0]));
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
