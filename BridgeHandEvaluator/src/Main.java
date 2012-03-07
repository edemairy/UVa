
import java.util.*;
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
        while (scanner.hasNextLine()) {
            String oneLine = scanner.nextLine();
            oneTestCase(oneLine);
        }
    }
    private static char[] types = {'S', 'H', 'D', 'C'};

    private static void oneTestCase(final String oneLine) {
        Set<Character> stopped = new HashSet<Character>();
        Map<Character, Set<Character>> cards = new HashMap<Character, Set<Character>>();
        for (char c : types) {
            cards.put(c, new HashSet<Character>());
        }
        int score = 0;
        int score4 = 0;

        for (int i = 0; i < oneLine.length(); i += 3) {
            char type = oneLine.charAt(i);
            char value = oneLine.charAt(i + 1);
            cards.get(value).add(type);
            if (type == 'A') {
                score += 4;
            }
            if (type == 'K') {
                score += 3;
            }
            if (type == 'Q') {
                score += 2;
            }
            if (type == 'J') {
                score += 1;
            }
        }
        for (char c : types) {
            if (cards.get(c).contains('A')) {
                stopped.add(c);
            }
            if (cards.get(c).contains('K') && cards.get(c).size() >= 2) {
                stopped.add(c);
            }
            if (cards.get(c).contains('Q') && cards.get(c).size() >= 3) {
                stopped.add(c);
            }
            if (cards.get(c).contains('K') && cards.get(c).size() == 1) {
                score -= 1;
            }
            if (cards.get(c).contains('Q') && cards.get(c).size() <= 2) {
                score -= 1;
            }
            if (cards.get(c).contains('J') && cards.get(c).size() <= 3) {
                score -= 1;
            }
        }
        score4 = score;
        for (char c : types) {
            if (cards.get(c).size() == 2) {
                score += 1;
            }
            if (cards.get(c).size() == 1) {
                score += 2;
            }
            if (cards.get(c).size() == 0) {
                score += 2;
            }
        }
        logger.log(Level.INFO, "score4={0}", score4);
        if (score < 14) {
            System.out.println("PASS");
        } else if ((score4 >= 16) && (stopped.size() == 4)) {
            System.out.println("BID NO-TRUMP");
        } else if (score >= 14) {
            char best = types[0];
            int sb = 0;
            for (char c : types) {
                if (cards.get(c).size() > sb) {
                    sb = cards.get(c).size();
                    best = c;
                }
            }
            System.out.println("BID " + best);
        }
    }
}
