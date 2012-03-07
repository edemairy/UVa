
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));

        int testCase = 1;
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            int result = oneTestCase(currentLine);
            System.out.println(result);
            ++testCase;
        }
    }
    private static int minM = 2;
    private static int minN = 2;
    private static int maxM = 10;
    private static int maxN = 10;

    private static int oneTestCase(String currentLine) {
        String[] parts = currentLine.split("/");

        char[][] board = new char[12][12];
        for (int i = 0; i < minM; ++i) {
            Arrays.fill(board[i], '#');
        }
        for (int i = minM; i < maxM; ++i) {
            Arrays.fill(board[i], '.');
            board[i][0] = '#';
            board[i][1] = '#';
            board[i][10] = '#';
            board[i][11] = '#';
        }
        for (int i = maxM; i < 12; ++i) {
            Arrays.fill(board[i], '#');
        }


        int line = minM;
        for (String part : parts) {
            if (part.equals("8")) {
                ++line;
                continue;
            }
            Pattern rg = Pattern.compile("\\d?\\D");
            Matcher matcher = rg.matcher(part);
            int col = minN;
            while (matcher.find()) {
                String subPart = matcher.group();
                if (subPart.length() == 1) {
                    write(board, line, col, subPart.charAt(0));
                } else {
                    col += subPart.charAt(0) - '0';
                    write(board, line, col, subPart.charAt(1));
                }
                ++col;
            }
            ++line;
        }
        for (int i = minM; i < maxM; ++i) {
            for (int j = minN; j < maxN; ++j) {
                markAll(board, i, j);
            }
        }
       // display(board);
        int result = 0;
        for (int i = minM; i < maxM; ++i) {
            for (int j = minN; j < maxN; ++j) {
                if (board[i][j] == '.') {
                    ++result;
                }
            }
        }
        return result;
    }

    private static void write(char[][] board, int line, int col, char charAt) {
        board[line][col] = charAt;
    }

    private static void markAll(char[][] board, int line, int col) {
        switch (Character.toLowerCase(board[line][col])) {
            case 'p': {
                if (board[line][col] == 'p') {
                    mark(board, line + 1, col + 1);
                    mark(board, line + 1, col - 1);
                } else {
                    mark(board, line - 1, col + 1);
                    mark(board, line - 1, col - 1);
                }
                break;
            }
            case 'n': {
                mark(board, line - 2, col - 1);
                mark(board, line - 2, col + 1);
                mark(board, line + 2, col - 1);
                mark(board, line + 2, col + 1);
                mark(board, line - 1, col - 2);
                mark(board, line - 1, col + 2);
                mark(board, line + 1, col - 2);
                mark(board, line + 1, col + 2);
                break;
            }
            case 'r':
                markR(board, line, col);
                break;
            case 'b':
                markB(board, line, col);
                break;
            case 'q':
                markB(board, line, col);
                markR(board, line, col);
                break;
            case 'k': {
                mark(board, line - 1, col - 1);
                mark(board, line - 1, col);
                mark(board, line - 1, col + 1);
                mark(board, line, col - 1);
                mark(board, line, col + 1);
                mark(board, line + 1, col - 1);
                mark(board, line + 1, col);
                mark(board, line + 1, col + 1);
                break;
            }
        }
    }

    private static void mark(char[][] board, int line, int col) {
        if (board[line][col] == '.') {
            board[line][col] = 'X';
        }
    }

    private static void display(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static void markB(char[][] board, int line, int col) {
        int[][] dirs = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        for (int[] dir : dirs) {
            markdDir(board, dir, line, col);
        }

    }

    private static void markR(char[][] board, int line, int col) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            markdDir(board, dir, line, col);
        }
    }

    private static void markdDir(char[][] board, int[] dir, int line, int col) {
        int d = 1;

        while ((board[line + dir[0] * d][col + dir[1] * d] == '.') || (board[line + dir[0] * d][col + dir[1] * d] == 'X')) {
            board[line + dir[0] * d][col + dir[1] * d] = 'X';
            ++d;
        }
    }
}
