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
public class Palyndrome {
	public static boolean isPalyndrome(String s) {
		int n = s.length();
		if (n == 0) {
			return true;
		}
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
