
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        nbTC = readInt(reader);
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append("Case ").append(tc).append(".");
            result.append(oneTestCase(reader));
//            if (tc < nbTC) {
            result.append('\n');
//            }
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        int nbBuildings = readInt(reader);
        Integer[] height = new Integer[nbBuildings];
        int[] width = new int[nbBuildings];
        for (int b = 0; b < nbBuildings; ++b) {
            height[b] = readInt(reader);
        }
        for (int b = 0; b < nbBuildings; ++b) {
            width[b] = readInt(reader);
        }

        int[] lis = new int[nbBuildings];
        int[] hlis = new int[nbBuildings];
        int maxLis = 0;
        for (int i = 0; i < nbBuildings; ++i) {
            int bestj = i;
            int bestw = width[i];
            for (int j = 0; j < i; ++j) {
                if ((hlis[j] < height[i]) && (lis[j] + width[i] > bestw)) {
                    bestw = lis[j] + width[i];
                    bestj = j;
                }
            }

            lis[i] = bestw;
            hlis[i] = height[i];
        }
        int[] lds = new int[nbBuildings];
        int[] hlds = new int[nbBuildings];
        int maxLds = 0;
        for (int i = 0; i < nbBuildings; ++i) {
            int bestj = i;
            int bestw = width[i];
            for (int j = 0; j < i; ++j) {
                if ((hlds[j] > height[i]) && (lds[j] + width[i] > bestw)) {
                    bestw = lds[j] + width[i];
                    bestj = j;
                }
            }

            lds[i] = bestw;
            hlds[i] = height[i];
        }
        Formatter formatterLis = new Formatter();
        int resLis = Integer.MIN_VALUE;
        for (int i=0; i<nbBuildings;++i) {
            resLis = Math.max(resLis, lis[i]);
        }
        formatterLis.format(" Increasing (%d).", resLis);

        Formatter formatterLds = new Formatter();
        int resLds = Integer.MIN_VALUE;
        for (int i=0; i<nbBuildings;++i) {
            resLds = Math.max(resLds, lds[i]);
        }
        formatterLds.format(" Decreasing (%d).", resLds);

        StringBuilder result = new StringBuilder();
        if (resLds > resLis) {
            result.append(formatterLds).append(formatterLis);
        } else {
            result.append(formatterLis).append(formatterLds);
        }
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
//        result.append(formatter.out());
        return result;
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
