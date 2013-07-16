
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formatter;
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
    public static int W, H;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        W = readInt(reader);
        H = readInt(reader);
        grid = new int[W + 1][H + 1];
        for (int i = 0; i < W + 1; i++) {
            Arrays.fill(grid[i], 0);
        }
//        scanner.nextLine();
//        for (int tc = 1; tc <= nbTC; ++tc) {
        result.append(oneTestCase(reader));
//        result.append('\n');
//        }
        System.out.print(result);
    }

    private static enum Dirs {

        NORTH('N', 0), EAST('E', 1), SOUTH('S', 2), WEST('W', 3);
        private char value;
        private int val;

        public Dirs right() {
            switch (this) {
                case NORTH:
                    return EAST;
                case EAST:
                    return SOUTH;
                case SOUTH:
                    return WEST;
                case WEST:
                    return NORTH;
            }
            return NORTH;
        }

        public Dirs left() {
            switch (this) {
                case NORTH:
                    return WEST;
                case EAST:
                    return NORTH;
                case SOUTH:
                    return EAST;
                case WEST:
                    return SOUTH;
            }
            return NORTH;
        }

        Dirs(char v, int vi) {
            this.value = v;
            this.val = vi;
        }

        public char value() {
            return value;
        }

        public int iValue() {
            return val;
        }
    };
    private static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// going = 
    private static int[][] grid;

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        try {
            while (true == true) {
                int x = readInt(reader);
                int y = readInt(reader);
                char d = readChar(reader);
                Dirs dir = Dirs.NORTH;

                switch (d) {
                    case 'E':
                        dir = Dirs.EAST;
                        break;
                    case 'W':
                        dir = Dirs.WEST;
                        break;
                    case 'S':
                        dir = Dirs.SOUTH;
                        break;
                    case 'N':
                        dir = Dirs.NORTH;
                        break;
                }
                reader.readLine();
                String command = reader.readLine();
                boolean lost = false;
                commandLoop:
                for (int i = 0; i < command.length(); i++) {
                    switch (command.charAt(i)) {
                        case 'R':
                            dir = dir.right();
                            break;
                        case 'L':
                            dir = dir.left();
                            break;
                        case 'F':
                            int oldx = x;
                            int oldy = y;
                            x += delta[dir.iValue()][0];
                            y += delta[dir.iValue()][1];
                            if (x < 0 || y < 0 || x > W || y > H) {
                                if (grid[oldx][oldy] == 0) {
                                    formatter.format("%d %d %c LOST\n", oldx, oldy, dir.value());
                                    grid[oldx][oldy] = -1;
                                    lost = true;
                                    break commandLoop;
                                } else {
                                    x = oldx;
                                    y = oldy;
                                }
                            }
                    }
                }
                if (!lost) {
                    formatter.format("%d %d %c\n", x, y, dir.value());
                }
            }
        } catch (Exception e) {
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
