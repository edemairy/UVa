
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
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
    private static int diffChar;
    private static HashMap<Character, Character> dico = new HashMap<Character, Character>();

    private static void learning() {
        for (int i = 0; i < inputs.length; ++i) {
            for (int c = 0; c < inputs[i].length(); ++c) {
                diffChar = outputs[i].charAt(c) - inputs[i].charAt(c);
            }
        }
    }
    static private String[] inputs = {
        "1JKJ'pz'{ol'{yhklthyr'vm'{ol'Jvu{yvs'Kh{h'Jvywvyh{pvu5",
        "1PIT'pz'h'{yhklthyr'vm'{ol'Pu{lyuh{pvuhs'I|zpulzz'Thjopul'Jvywvyh{pvu5",
        "1KLJ'pz'{ol'{yhklthyr'vm'{ol'Kpnp{hs'Lx|pwtlu{'Jvywvyh{pvu5"
    };
    private static String[] outputs = {
        "*CDC is the trademark of the Control Data Corporation.",
        "*IBM is a trademark of the International Business Machine Corporation.",
        "*DEC is the trademark of the Digital Equipment Corporation."
    };
    static DataInputStream in = new DataInputStream(System.in);
    static DataOutputStream output = new DataOutputStream(System.out);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        scanner.nextLine();
//        learning();
        diffChar = -7;
        try {
            while (!EOF) {
                oneTestCase();
            }
        } catch (IOException ex) {
        }

    }

    private static void oneTestCase() throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);

//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }


        int currentChar;
        dochar:
        do {
            currentChar = in.read();
            if (currentChar == -1) {
                EOF = true;
                break dochar;
            }
            if ((currentChar == '\n') || (currentChar == '\r')) {
                output.writeByte(currentChar);
            } else {
                output.writeByte(currentChar - 7);
            }

        } while (true);


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
    private static boolean EOF = false;

    private static char readChar(BufferedReader reader) throws IOException {
        int result = reader.read();
        if (result == -1) {
            EOF = true;
            throw new IOException("No more characters.");
        }
        return (char) result;
    }
}
