/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competitiveprogramming;

import java.util.BitSet;

/**
 *
 * @author Erwan Demairy <Erwan.Demairy@inria.fr>
 */
public class Graph {
	private int n;
	private BitSet[] graph;
	public Graph(int n) {
		this.n = n;
		graph = new BitSet[n]; 
		for (int i=0; i<n; i++) {
			graph[i] = new BitSet(n);
		}
	}	
	public void addEdge(int s, int e) {
		graph[s].set(e);
		graph[e].set(s);
	}
	public int[] getNeighbours(int n) {
		int[] result = new int[graph[n].cardinality()];
		for (int i=graph[n].nextSetBit(0), cpt=0; i != -1; i=graph[n].nextSetBit(i+1), cpt++) {
			result[cpt] = i;			
		}
		return result;
	}

	int getNodesNumber() {
		return n;
	}
}
