/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

class Permutations {

	int size;
	int[] values;
	BitSet chosen;

	public Permutations(int n) {
		size = n;
		values = new int[n];
		chosen = new BitSet(size);
	}

	public static int[] nextIterations(int[] current) throws Exception {
		int i = current.length - 1;
		int border;
		while (i > 0 && current[i - 1] > current[i]) {
			i--;
		}
		border = i - 1;
		if (border != -1) {
			i = current.length - 1;
			while (border < i && current[border] > current[i]) {
				i--;
			}
			swap(current, i, border);
			for (int start = border + 2, end = current.length - 1; start < end; start++, end--) {
				swap(current, start, end);
			}
			i = border + 1;
			while (i + 1 < current.length && current[i] > current[i + 1]) {
				swap(current, i, i + 1);
			}
		} else {
			throw new Exception("Last iteration");
		}
		return current;
	}

	private static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	private void print(int level) {
		if (level == size) {
			for (int i = 0; i < size; i++) {
				System.out.format("%d ", values[i]);
			}
			System.out.format("%n");
		} else {
			for (int i = 0; i < size; i++) {
				if (!chosen.get(i)) {
					chosen.set(i);
					values[level] = i;
					print(level + 1);
					chosen.clear(i);
				}
			}
		}
	}

	public void printList() {
		print(0);
	}

}

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class CompetitiveProgramming {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.Exception
	 */
	public static void main(String[] args) throws Exception {
		String[] inputs = {"1.4732", "15.324547327"};
		for (String input : inputs) {
			System.out.format(Locale.ENGLISH, "%7.3f%n", Double.parseDouble(input));
		}
		for (int i = 0; i < 16; i++) {
			System.out.format(Locale.ENGLISH, "%." + i + "f%n", Math.PI);
		}
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 8, 9);
		int val = cal.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
		String text = formatter.format(val);
		System.out.println(text);

		int[] numbers = {1, 4, 7, 3, 2, 1, -10};
		Set<Integer> set = new TreeSet<>();
		for (int number : numbers) {
			set.add(number);
		}
		for (int number : set) {
			System.out.format("%d ", number);
		}
		System.out.format("%n");

		Permutations p = new Permutations(3);
		p.printList();

		int[] current = {0, 1, 2, 3, 4, 5, 6};
		try {
			while (true) {
				for (int i = 0; i < current.length; i++) {
					System.out.format("%d ", current[i]);
				}
				System.out.format("%n ");
				current = Permutations.nextIterations(current);
			}
		} catch (Exception ex) {
		}
	}

}
