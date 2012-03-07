
import java.util.Scanner;
import java.util.TreeMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edemairy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TreeMap<String, Integer> num = new TreeMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i=0; i<N; ++i) {
            String country = scanner.next();
            if (num.get(country)!= null) {
                num.put(country,num.get(country)+1);
            } else {
                num.put(country,1);
            }
            scanner.nextLine();
        }
        for (String country:num.keySet()) {
            System.out.println(country+" "+num.get(country));
        }
    }
}
