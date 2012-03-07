
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
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
        String currentLine;
        Pattern p = Pattern.compile("[a-zA-Z]+", Pattern.DOTALL);
        Pattern q = Pattern.compile("[^a-zA-Z]+", Pattern.DOTALL);

        do {
            while (scanner.hasNextLine()) {
                int nbw = 0;
                currentLine = scanner.nextLine();
                Scanner scanLine = new Scanner(currentLine);
                scanLine.useDelimiter(q);
                while (scanLine.hasNext(p)) {
                    String word = scanLine.next(p);
                    nbw++;
                    logger.log(Level.INFO, "word = {0}", word);
                }
                System.out.println(nbw);
            }
        } while (scanner.hasNext(p) || scanner.hasNext(q));
    }
}
