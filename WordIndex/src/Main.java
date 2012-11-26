
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
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

        build();
//        scanner.nextLine();
        mainloop:
        while (true) {
            String current = reader.readLine();
            if (current == null) {
                break mainloop;
            }
            result.append(oneTestCase(current));
            result.append('\n');
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(String current) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        int r = -1;
        searchloop: 
        for (int i=0; i<dico.size(); ++i) {
            if (dico.get(i).equals(current)) {
                r = i;
            }
        }
        output.append(r+1);
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

    static ArrayList<String> dico = new ArrayList<String>();
    static String[] ordered = new String[0];
    private static void build() {
        ArrayList<String> nd = new ArrayList<String>();
        for (char c='a' ; c<='z'; ++c) {
            nd.add(""+c);
        }
        dico.addAll(nd);
        for (int i=2; i<=5; ++i) {       
            ArrayList<String> lastd = nd;
            nd = new ArrayList<String>();
            for (String s:lastd) {
                for (char c = (char)(s.charAt(s.length()-1)+1); c <= 'z'; ++c) {
                    nd.add(s.concat(""+c));
                }
            }
            dico.addAll(nd);
        }
    }
}
