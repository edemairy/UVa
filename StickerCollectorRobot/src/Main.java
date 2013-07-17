
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

    private static int nbTC;
    private StringBuilder result = new StringBuilder();

    public void run() throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        nbTC = readInt(reader);
//        scanner.nextLine();
//        for (int tc = 1; tc <= nbTC; ++tc) {
        try {
            while (1 > 0) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (EndException ex) {
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

    private StringBuilder oneTestCase(BufferedReader reader) throws IOException, EndException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        int N = readInt(reader);
        int M = readInt(reader);
        int S = readInt(reader);
        if (N == 0 && M == 0 && S == 0) {
            throw new EndException();
        }
        int row = -1;
        int col = -1;
        char dir = ' ';

        StringBuilder[] grid = new StringBuilder[N];
        for (int i = 0; i < N; i++) {
            grid[i] = new StringBuilder(reader.readLine());
            for (int j = 0; j < M; j++) {
                char current = grid[i].charAt(j);
                switch (current) {
                    case 'N':
                    case 'S':
                    case 'L':
                    case 'O':
                        dir = current;
                        row = i;
                        col = j;
                }
            }
        }
        String commands = reader.readLine();
        int r = 0;
        for (int i = 0; i < S; i++) {
            int lastrow = row;
            int lastcol = col;
            switch (commands.charAt(i)) {
                case 'D':
                    switch (dir) {
                        case 'N':
                            dir = 'L';
                            break;
                        case 'L':
                            dir = 'S';
                            break;
                        case 'S':
                            dir = 'O';
                            break;
                        case 'O':
                            dir = 'N';
                            break;
                    }
                    break;
                case 'E':
                    switch (dir) {
                        case 'N':
                            dir = 'O';
                            break;
                        case 'L':
                            dir = 'N';
                            break;
                        case 'S':
                            dir = 'L';
                            break;
                        case 'O':
                            dir = 'S';
                            break;
                    }
                    break;
                case 'F':
                    switch (dir) {
                        case 'N':
                            row--;
                            break;
                        case 'L':
                            col++;
                            break;
                        case 'S':
                            row++;
                            break;
                        case 'O':
                            col--;
                            break;
                    }
                    break;
            }
            if (row < 0 || col < 0 || row >= N || col >= M) {
                row = lastrow;
                col = lastcol;
            }
            switch (grid[row].charAt(col)) {
                case '*':
                    r++;
                    grid[row].setCharAt(col, '.');
                    break;
                case '#':
                    row = lastrow;
                    col = lastcol;
                    break;
            }

        }
        formatter.format("%d", r);
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

    private static class EndException extends Exception {
    }
}
