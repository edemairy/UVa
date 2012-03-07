
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
        int nbTC = readInt(reader);
        for (int tc = 1; tc <= nbTC; ++tc) {
            if (tc > 1) {
                result.append('\n');
            }
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    public static TreeMap<Integer, Integer> garmy = new TreeMap<Integer, Integer>();
    public static TreeMap<Integer, Integer> barmy = new TreeMap<Integer, Integer>();
    public static ArrayList<Integer> ground = new ArrayList<Integer>(100001);
    public static ArrayList<Integer> bround = new ArrayList<Integer>(100001);

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        int B = readInt(reader);
        int SG = readInt(reader);
        int SB = readInt(reader);
        garmy.clear();
        for (int g = 0; g < SG; ++g) {
            addMap(garmy, readInt(reader));
        }
        barmy.clear();
        for (int b = 0; b < SB; ++b) {
            addMap(barmy, readInt(reader));
        }

        while (!barmy.isEmpty() && !garmy.isEmpty()) {
            int nbBActive = Math.min(B, Math.min(sizeMap(garmy), sizeMap(barmy)));
            ground.clear();
            bround.clear();
            for (int b = 0; b < nbBActive; ++b) {
                ground.add(removeLast(garmy));
                bround.add(removeLast(barmy));
            }
            for (int b = 0; b < nbBActive; ++b) {
                int r = ground.get(b) - bround.get(b);
                if (r > 0) {
                    addMap(garmy, r);
                } else if (r < 0) {
                    addMap(barmy, -r);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        if (barmy.isEmpty() && garmy.isEmpty()) {
            result.append("green and blue died");
        } else if (barmy.isEmpty()) {
            result.append("green wins");
            while (!garmy.isEmpty()) {
                result.append("\n").append(removeLast(garmy));
            }
        } else if (garmy.isEmpty()) {
            result.append("blue wins");
            while (!barmy.isEmpty()) {
                result.append("\n").append(removeLast(barmy));
            }
        }
        return result;
    }

    private static Integer removeLast(TreeMap<Integer, Integer> garmy1) {
        Integer lastKey = garmy1.lastKey();
        if (garmy1.get(lastKey) == 1) {
            garmy1.remove(lastKey);
        } else {
            garmy1.put(lastKey, garmy1.get(lastKey) - 1);
        }
        return lastKey;
    }
    private static Integer sizeMap(TreeMap<Integer, Integer> garmy1) {
        int result = 0;
        for (Integer i:garmy1.values()) {
            result += i;
        }
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
        char currentChar = (char) reader.read();
        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        return result;
    }

    private static void addMap(TreeMap<Integer, Integer> barmy, int readInt) {
        if (barmy.get(readInt) == null) {
            barmy.put(readInt, 1);
        } else {
            barmy.put(readInt, barmy.get(readInt) + 1);
        }
    }
}
