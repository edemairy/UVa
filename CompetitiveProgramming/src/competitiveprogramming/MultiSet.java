/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 * @param <E> Type of the multiset.
 */
public class MultiSet<E> {

	private TreeMap<E, Integer> data;

	public MultiSet() {
		data = new TreeMap<>();
	}

	public boolean add(E e) {
		boolean result = data.keySet().contains(e);
		if (!result) {
			data.put(e, 0);
		}
		data.put(e, data.get(e) + 1);
		return result;
	}

	public boolean remove(E e) {
		boolean result = data.keySet().contains(e);
		if (result) {
			data.put(e, data.get(e) - 1);
		}
		if (data.get(e) == 0) {
			data.remove(e);
		}
		return result;
	}

	public E pollFirst() {
		E firstKey = data.firstKey();
		remove(firstKey);
		return firstKey;
	}

	public E pollLast() {
		E lastKey = data.lastKey();
		remove(lastKey);
		return lastKey;
	}
}
