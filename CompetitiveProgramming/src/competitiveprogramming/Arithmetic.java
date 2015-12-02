/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class Arithmetic {
	private long pgcd(long a, long b) {
		long r = a;
		while (r != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return (Math.abs(a));
	}
	public static long mod(long a, long b) {
		long r = a % b;
		if (r < 0) {
			r += b;
		}
		return r;
	}
}
