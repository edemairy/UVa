
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author edemairy
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        Scanner scanner = new Scanner(System.in);
        boolean first = true;
        while (scanner.hasNext()) {
            String currentLine = scanner.nextLine();
            logger.log(Level.INFO, "currentLine = {0}", currentLine);
            StringBuilder result = new StringBuilder();
            int lastpos = -1;
            int oldpos = -1;

            logger.log(Level.INFO, "before loop");

            while ((lastpos = currentLine.indexOf("\"", lastpos + 1)) != -1) {
                logger.log(Level.INFO, "lastpos = {0}, oldpos = {1}", new Object[]{lastpos, oldpos});
                if (lastpos >= oldpos + 1) {
                    result.append(currentLine.substring(oldpos + 1, lastpos));
                }
                if (first) {
                    result.append("``");
                } else {
                    result.append("''");
                }
                first = !first;
                oldpos = lastpos;
            }
            result.append(currentLine.substring(oldpos + 1, currentLine.length()));
            System.out.println(result);
        }
    }
}
