/* UVA 499
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author edemairy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            int[] al = new int[256];
            Arrays.fill(al, 0);
            for (int i = 0; i < currentLine.length(); ++i) {
                al[ currentLine.charAt(i)]++;
            }
            Set<Character> maxs = new TreeSet<Character>();
            int max = 0;
            for (int i = 0; i < 256; ++i) {
                if (((i >= 'a') && (i <= 'z')) || ((i >= 'A') && (i <= 'Z'))) {
                    if (al[i] > max) {
                        maxs = new TreeSet<Character>();
                    }
                    if (al[i] >= max) {
                        max = al[i];
                        maxs.add(new Character((char) i));
                    }

                }

            }
            if (max != 0) {
                for (Character c : maxs) {
                    if (Character.isUpperCase(c)) {
                        System.out.print(c);
                    }
                }
                for (Character c : maxs) {
                    if (Character.isLowerCase(c)) {
                        System.out.print(c);
                    }
                }
                System.out.println(" " + max);
            } else {
                System.out.println();
            }
        }
    }
}
