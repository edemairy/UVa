
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
    private static int B;
    private static int W;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        B = scanner.nextInt();
        W = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        while ((B != 0) || (W != 0)) {
            result.append(oneTestCase(B, W, scanner));
            B = scanner.nextInt();
            W = scanner.nextInt();

            result.append('\n');

        }
        System.out.print(result);
    }
    static int[][] maze = new int[1000][1000];
    private static int posb, posw;
    private static Dirs dir;

    private static void moveForward() {
        ++maze[posb][posw];
        posb += db[dir.ordinal()];
        posw += dw[dir.ordinal()];
        if (!isRightWall() && ((posb != B) || (posw != 1))) {
            turnRight();
            moveForward();
        }
    }

    private static void turnRight() {
        dir = Dirs.values()[(dir.ordinal() + 1) % 4];
    }

    private static void turnLeft() {
        dir = Dirs.values()[(dir.ordinal() + 3) % 4];
    }

    private static boolean isForwardFree() {
        return (maze[posb + db[dir.ordinal()]][posw + dw[dir.ordinal()]] != -1);
    }

    enum Dirs {

        North, East, South, West
    };
    static int db[] = {-1, 0, 1, 0};
    static int dw[] = {0, 1, 0, -1};

    private static StringBuilder oneTestCase(int B, int W, Scanner scanner) {
        scanner.nextLine();
        Arrays.fill(maze[0], -1);
        for (int b = 1; b <= B; ++b) {
            maze[b][0] = maze[b][W + 1] = -1;
            String currentLine = scanner.nextLine();
            int w = 1;
            for (char c : currentLine.toCharArray()) {
                maze[b][w] = (c == '0') ? 0 : -1;
                ++w;
            }
        }
        Arrays.fill(maze[B + 1], -1);
        if ((maze[B][0] == -1) && (maze[B][2] == -1) && (maze[B - 1][1] == -1) && (maze[B + 1][1] == -1)) {
            StringBuilder result = new StringBuilder();
            result.append("  1  0  0  0  0");
            return result;
        }
        boolean firstMove = true;
        posb = B;
        posw = 1;
        dir = Dirs.East;
        while (firstMove || ((posb != B) || (posw != 1))) {
            if (isRightWall()
                    && isForwardFree()) {
                moveForward();
                firstMove = false;
            } else {
                turnLeft();
            }
        }
        int n[] = new int[5];
        for (int b = 1; b <= B; ++b) {
            for (int w = 1; w <= W; ++w) {
                int val = maze[b][w];
                if ((val < 5) && (val >= 0)) {
                    ++n[val];
                }
            }
        }
        Formatter formatter = new Formatter();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; ++i) {
            formatter.format("%3d", n[i]);
        }
        result.append(formatter.out());
        return result;
    }

    private static boolean isRightWall() {
        Dirs rightDir = Dirs.values()[(dir.ordinal() + 1) % 4];
        return (maze[posb + db[rightDir.ordinal()]][posw + dw[rightDir.ordinal()]] == -1);
    }
}
