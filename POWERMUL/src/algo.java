/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class algo {
	public static void main(String[] args){
		double a,b,m;
		a=-10;
		b=10;
		while (b-a > 0.0001) {
			m = (a+b)/2;
			if (0.1*m*m*m*Math.exp(-m)+3*m*m-1 > 0) {
				b = m;
			} else {
				a = m;
			}
		}
		System.out.println(a);
		System.out.println(b);
	}	
}
