
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

        String currentWord;
        HashMap<String, List<String>> dict = new HashMap<String, List<String>>();
        while ((scanner.hasNext()) && !(currentWord = scanner.next()).equals("#")) {
            char[] reduc = currentWord.toLowerCase().toCharArray();
            Arrays.sort(reduc);
            String sreduc = new String(reduc);
            if (dict.get(sreduc) == null) {
                dict.put(sreduc, new ArrayList<String>());
            }
            dict.get(sreduc).add(currentWord);
        }
        TreeSet<String> result = new TreeSet<String>();
        for (String key : dict.keySet()) {
            if (dict.get(key).size() == 1) {
                result.add(dict.get(key).get(0));
            }
        }
        for (String key : result) {
            System.out.println(key);
        }
    }
}
