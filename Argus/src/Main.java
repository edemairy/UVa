
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.PriorityQueue;
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

        result.append(oneTestCase(reader));
      
        System.out.print(result);
    }

    public static class Item implements Comparable {

        public Item(int id, int time) {
            this.time = time;
            this.id = id;
        }
        int id;
        int time;

        @Override
        public int compareTo(Object o) {
            Item other = (Item) o;
            if (other.time != this.time) {
                return this.time - other.time;
            } else {
                return this.id - other.id;
            }
        }
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        PriorityQueue<Item> pq = new PriorityQueue<Item>();
        readloop:
        do {
            String currentLine = reader.readLine();
            if (currentLine.equals("#")) {
                break readloop;
            }
            String[] parts = currentLine.split(" +");
            int id = Integer.parseInt(parts[1]);
            int period = Integer.parseInt(parts[2]);
            for (int p = period; p <= 10000; p += period) {
                pq.add(new Item(id, p));
            }
        } while (true);
        int nbRequests = Integer.parseInt(reader.readLine());
        for (int i = 0; i < nbRequests; i++) {
            formatter.format("%d\n", pq.poll().id);
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
