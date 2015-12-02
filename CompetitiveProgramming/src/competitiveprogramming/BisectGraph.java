/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class BisectGraph {

	public static class SolBisect {
		public int[] colors;
		ArrayList<ArrayList<Integer>> cfc;
	};

	public static SolBisect bisect(Graph g) {
		int n = g.getNodesNumber();
		ArrayList<ArrayList<Integer>> cfc = new ArrayList<>();
		int[] color = new int[n];
		Arrays.fill(color, 0);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
		boolean isBicolor = true;
		color[0] = 1;
		int curColor = 2;
		int oColor = 1;
		int numCfc = 0;
		cfc.add(new ArrayList<Integer>());
		cfc.get(0).add(0);
		mainloop:
		while (!queue.isEmpty()) {
			ArrayList<Integer> nextqueue = new ArrayList<Integer>();
			while (!queue.isEmpty()) {
				int cur = queue.remove(queue.size() - 1);
				int[] neighbours = g.getNeighbours(cur);
				for (int i = 0; i < neighbours.length; i++) {
					int neighbour = neighbours[i];
					if (color[neighbour] == 0) {
						cfc.get(numCfc).add(neighbour);
						color[neighbour] = curColor;
						nextqueue.add(neighbour);
					} else if (color[neighbour] != curColor) {
						isBicolor = false;
						break mainloop;
					}
				}
			}
			queue = nextqueue;
			if (queue.isEmpty()) {
				for (int i = 0; i < n; i++) {
					if (color[i] == 0) {
						color[i] = curColor;
						queue.add(i);
						cfc.add(new ArrayList<Integer>());
						numCfc++;
						cfc.get(numCfc).add(i);
						break;
					}
				}
			}
			if (curColor == 1) {
				curColor = 2;
			} else {
				curColor = 1;
			}
		}
		if (isBicolor) {
			SolBisect sol = new SolBisect();
			sol.colors = color;
			sol.cfc = cfc;
			return sol;
		} else {
			return null;
		}
	}

}
