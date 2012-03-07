
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);

        int testCase = 1;
        String currentLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));
        while (!(currentLine = in.readLine()).equals("Game Over")) {
            int result = oneTestCase(currentLine);
            out.append(Integer.toString(result));
            out.append("\n");
            ++testCase;
        }
        out.close();
    }
    private static int[] rolls = new int[30];
    private static boolean[] isStrike = new boolean[30];
//        Arrays.fill(isStrike, false);
    private static boolean[] isSpare = new boolean[30];
//        Arrays.fill(isSpare, false);
    private static boolean[] isFrame = new boolean[30];

    private static int oneTestCase(String currentLine) throws IOException {
        logger.log(Level.INFO, "currentLine = {0}", currentLine);
        BufferedReader in = new BufferedReader(new StringReader(currentLine));
        Arrays.fill(isStrike, false);
        Arrays.fill(isSpare, false);
        Arrays.fill(isFrame, false);

        int numRoll = 0;
        boolean oneRoll = false;
        char[] line = new char[50];
        int length = in.read(line);
        for (int nc = 0; nc < length; ++nc) {
            if (' ' == line[nc]) continue;
            switch (line[nc]) {
                case '/':
                    rolls[numRoll] = 10 - rolls[numRoll - 1];
                    isSpare[numRoll] = true;
                    isStrike[numRoll] = false;
                    isFrame[numRoll] = true;
                    oneRoll = false;
                    break;
                case 'X':
                    rolls[numRoll] = 10;
                    isSpare[numRoll] = false;
                    isStrike[numRoll] = true;
                    isFrame[numRoll] = true;
                    oneRoll = false;
                    break;
                default: {
                    isSpare[numRoll] = false;
                    isStrike[numRoll] = false;
                    rolls[numRoll] = line[nc]-'0';
                    isFrame[numRoll] = oneRoll;
                    oneRoll = !oneRoll;
                }
            }
            numRoll++;
        }
        int result = 0;
        int numFrame = 0;
        for (int i = 0; i < numRoll; ++i) {
            if (isFrame[i] && (numFrame < 10)) {
                numFrame++;
                if (isStrike[i]) {
                    result += 10 + rolls[i + 1] + rolls[i + 2];
                } else if (isSpare[i]) {
                    result += 10 + rolls[i + 1];
                } else {
                    result += rolls[i] + rolls[i - 1];
                }
                logger.log(Level.INFO, "{0} result = {1}", new Object[]{i, result});
            }
        }
        return result;
    }
}
