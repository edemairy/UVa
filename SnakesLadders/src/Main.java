
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy The first line is the number of test cases to follow. The
 * test cases follow, one after another; the format of each test case is the
 * following:
 *
 * The first line contains three positive integers: the number a of players, the
 * number b of snakes or ladders, and the number c of die rolls. There will be
 * no more than 1000000 players and no more than 1000000 die rolls. Each of the
 * next b lines contains two integers specifying a snake or ladder. The first
 * integer indicates the square containing the mouth of the snake or the bottom
 * of the ladder. The second integer indicates the square containing the tail of
 * the snake or the top of the ladder. The following c lines each contain one
 * integer giving number rolled on the die.
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
        int nbTC = scanner.nextInt();
        for (int testCase = 1; testCase <= nbTC; ++testCase) {
            oneTestCase(scanner);
        }
    }

    private static void oneTestCase(Scanner scanner) {
        int nbPlayers = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        HashMap<Integer, Integer> mv = new HashMap<Integer, Integer>();
        for (int line = 0; line < b; ++line) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            mv.put(s, e);
        }
        int[] pos = new int[nbPlayers];
        boolean ended = false;
        Arrays.fill(pos, 1);
        for (int cptDice = 0; cptDice < c; ++cptDice) {
            int player = cptDice % nbPlayers;
            int dice = scanner.nextInt();
            if (!ended) {
                pos[player] += dice;
                Integer e = mv.get(pos[player]);
                if (e != null) {
                    pos[player] = e;
                }
                if (pos[player] >=100) {
                    pos[player]=100;
                    ended = true;
                }
            }

        }
        for (int i = 0; i < nbPlayers; ++i) {
            System.out.println("Position of player " + (i + 1) + " is " + pos[i] + ".");
        }
    }
}
