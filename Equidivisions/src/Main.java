
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author edemairy
 */
public class Main {

    private int nbTC;
    private StringBuilder result = new StringBuilder();

    private static class EndException extends RuntimeException {
    }

    public void run() throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        nbTC = readInt(reader);
         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
        try {
            for (int tc = 1; tc <= nbTC; ++tc) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (EndException e) {
        }
        System.out.print(result);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        int N = readInt(reader);
        if (N == 0) {
            throw new EndException();
        }
        int[] size = new int[N];
        int sizet = 0;
        int[][] grid = new int[N + 2][N + 2];
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(grid[i], N-1);
            grid[i][0] = grid[i][N + 1] = -1;
        }
        Arrays.fill(grid[0], -1);
        Arrays.fill(grid[N + 1], -1);

        for (int i = 0; i < N - 1; i++) {
            String currentLine = reader.readLine();
            String[] parts = currentLine.split(" ");
            assert (parts.length % 2 == 0);
            for (int j = 0; j < parts.length; j += 2) {
                int row = Integer.parseInt(parts[j]);
                int col = Integer.parseInt(parts[j + 1]);
                grid[row][col] = i;
            }
            size[i] = parts.length / 2;
            sizet += size[i];
        }
        size[N - 1] = N * N - sizet;
        boolean ok = true;
        mainloop:
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i][j] != -1) {
                    int color = grid[i][j];
                    int sizeArea = 0;
                    grid[i][j] = -1;
                    LinkedList<int[]> queue = new LinkedList<int[]>();
                    queue.push(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        sizeArea++;
                        int[] current = queue.pollLast();
                        for (int[] dir : dirs) {
                            int nextRow = current[0] + dir[0];
                            int nextCol = current[1] + dir[1];
                            if (grid[nextRow][nextCol] == color ) {
                                queue.push(new int[]{nextRow, nextCol});
                                grid[nextRow][nextCol] = -1;
                            }
                        }
                    }
                    if (sizeArea != N) {
                        ok = false;
                        break mainloop;
                    } else {
                        size[color] -= sizeArea;
                    }
                }
            }
        }
        formatter.format((ok) ? "good" : "wrong");

        output.append(formatter.out());
        return output;
    }
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int readInt(BufferedReader reader) throws IOException {
        int r = 0;
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
            r = r * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return r;
        } else {
            return -r;
        }
    }

    private char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
