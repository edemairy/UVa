
import java.util.LinkedList;
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
    private static int MAXI = 1000000;
    private static int[] next = new int[MAXI + 2];
    private static int[] length = new int[MAXI + 2];

    public static void main(String[] args) {
        logger.setLevel(Level.OFF);
        for (int i = 0; i <= (MAXI / 2); ++i) {
            next[2 * i] = i;
            next[2 * i + 1] = 3 * (2 * i + 1) + 1;
        }
        length[1] = 1;
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getResourceAsStream("/input.txt"));
        try {
            for (;;) {
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                int mi = Math.min(i, j);
                int mj = Math.max(i, j);

                logger.log(Level.INFO, "i={0}, j={1}", new Object[]{i, j});
                int r = 0;
                for (int cpt = mi; cpt <= mj; ++cpt) {
//                logger.log(Level.INFO, "cpt={0}", cpt);
                    if (length[cpt] == 0) {
                        LinkedList<Long> si = new LinkedList<Long>();
                        long cur = cpt;
                        int nbcur = 1;
                        while ((cur != 1)) {
//                        logger.log(Level.INFO, "cur = {0}", cur);
                            si.add(cur);
                            if (cur <= MAXI) {
                                cur = next[(int) cur];
                            } else {
                                if (cur % 2 == 0) {
                                    cur /= 2;
                                } else {
                                    cur = 3 * cur + 1;
                                }
                            }
                            nbcur++;
                        }
                        while (!si.isEmpty()) {
                            if (si.getFirst() <= MAXI) {
                                length[(si.getFirst()).intValue()] = nbcur;
                            }
//                        System.err.println(si.getLast()+":"+nbcur+" ");
                            --nbcur;
                            si.removeFirst();
                        }
                    }
                    r = Math.max(r, length[cpt]);
                }
                System.out.println(i + " " + j + " " + r);
                // TODO code application logic here
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.info("end of the input");
        }
    }
}
