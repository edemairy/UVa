/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class Anagram implements Comparable<Anagram> {
	Map<Character, Integer> frequency = new HashMap<Character, Integer>();
	public Anagram(String s) {
		String word = s.toLowerCase();
		for (char c = 'a'; c<='z'; c++) {
			frequency.put(c,0);
		}
		for (char c: word.toCharArray()) {
			frequency.put(c, frequency.get(c)+1);
		}
	}	

	@Override
	public int compareTo(Anagram o) {
		if (frequency.equals(o.frequency)) {
			return 0;
		}
		for (char c:frequency.keySet()) {
			int diff = frequency.get(c) - o.frequency.get(c);
			if (diff != 0)	{
				return diff;
			}
		}
		return 0;
	}
	
}
