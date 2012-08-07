
import java.util.Arrays;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edemairy
 */
public class BuildTC {

    
    public static void main(String[] args) {
        int M = 10000;
        int N = 1000000;
        int[] vals = new int[N+10];
        for (int i = 1; i <= N; i++) {
            vals[i] = i;
        }
        System.out.println(N+" "+M);
        Random r = new Random();
        for (int i = 0; i < M; i++) {
            int w = r.nextInt(2)+1;
            int x = r.nextInt(N-1)+1;
            int y = r.nextInt(N-1)+1;
            int t = Math.min(x, y);
            y = Math.max(x, y);
            x = t;
            int z = r.nextInt(100000);
            System.out.println(w+" "+x+" "+y+" "+z);
            if (w==2) z=-z;
            for (int j=x; j<=y; ++j) {
                vals[j] += z;
            }
        }
        Arrays.sort(vals,1,N+1);
        System.out.println("result = "+(vals[N]-vals[1]));
    }
}
