
import java.util.ArrayList;
import java.util.BitSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class generRandomTestCases {
public static void main(String[] argv){
	for (int i=0; i<5000; i++) {
		BitSet used = new BitSet(53);
		ArrayList<Integer> ai = new ArrayList<Integer>();
		for (int j=0; j<5; j++) {
			int card;
			do {
				card = (int)(Math.random()*52 + 1);
			} while(used.get(card));
			used.set(card);
			System.out.print(card);
			if (j<4) {
				System.out.print(" ");
			} else {
				System.out.format("%n");
			}
		}
	}
}	
}
