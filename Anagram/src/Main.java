
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        int nbTC = scanner.nextInt();
        for (int testCase = 1; testCase <= nbTC; ++testCase) {
            String word = scanner.next();
            Set<String> result = oneTestCase(word);
            for (String anag : result) {
                System.out.println(anag);
            }
        }
    }

    private static Set<String> oneTestCase(String currentLine) {
        Set<String> result = new TreeSet<String>(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                for (int i = 0; i < Math.min(o1.length(), o2.length()); ++i) {
                    char c1 = o1.charAt(i);
                    char c2 = o2.charAt(i);
                    if (c1 == c2) {
                        continue;
                    } else {
                        if (Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
                            return (Character.isUpperCase(c1)) ? -1 : 1;
                        } else {
                            return Character.toLowerCase(c1) - Character.toLowerCase(c2);
                        }
                    }
                }
                return (o1.length()-o2.length());
            }
        });
        char[] word = currentLine.toCharArray();
        Arrays.sort(word);
        result.add(new String(word));

        while (next(word)) {
            result.add(new String(word));
        }
        return result;
    }

    private static boolean next(char[] word) {
        /*
         * Find the largest index k such that a[k] < a[k + 1]. If no such index
         * exists, the permutation is the last permutation. Find the largest
         * index l such that a[k] < a[l]. Since k + 1 is such an index, l is
         * well defined and satisfies k < l. Swap a[k] with a[l]. Reverse the
         * sequence from a[k + 1] up to and including the final element a[n].
         */
        boolean foundk = false;
        int k;
        searchk:
        for (k = word.length - 2; k >= 0; --k) {
            if (word[k] < word[k + 1]) {
                foundk = true;
                break searchk;
            }
        }
        if (!foundk) {
            return false;
        }
        int l;
        searchl:
        for (l = word.length - 1; l > k; --l) {
            if (word[k] < word[l]) {
                break searchl;
            }
        }
        char t = word[l];
        word[l] = word[k];
        word[k] = t;
        char[] temp = Arrays.copyOfRange(word, k + 1, word.length);
        for (int i = 0; i < word.length - k - 1; ++i) {
            word[k + 1 + i] = temp[temp.length - i - 1];
        }
        return true;
    }
}
