
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
            result.append(oneTestCase(reader));
            result.append('\n');
        }
        System.out.print(result);
    }
    private static HashMap<Character, Integer> chars = new HashMap<Character, Integer>(100);
    private static int[][] grid = new int[5][5];

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        for (int i = 0; i < 5; ++i) {
            Arrays.fill(grid[i], 0);
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i + 1][j + 1] = (readNextChar(reader)) - '0';
            }
        }



        StringBuilder result = new StringBuilder();
        if (isGridNull()) {
            result.append(-1);
            return result;
        } else {
            int r = 0;
            while (!isGridNull()) {
                nextGrid();
                ++r;
            }
            result.append(r-1);
            return result;
        }


    }

    private static boolean isGridNull() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (grid[i + 1][j + 1] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void nextGrid() {
        int[][] newGrid = new int[5][5];
        for (int i = 0; i < 5; ++i) {
            Arrays.fill(newGrid[i], 0);
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                newGrid[i + 1][j + 1] = (grid[i + 1][j] + grid[i + 1][j + 2] + grid[i][j + 1] + grid[i + 2][j + 1]) % 2;
            }
        }
        grid = newGrid;
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

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }

    private static char readNextChar(BufferedReader reader) throws IOException {
        char result;
        do {
            result = (char) reader.read();
        } while ((result == '\n') || (result == ' ') || (result == '\t'));
        return result;
    }
}
