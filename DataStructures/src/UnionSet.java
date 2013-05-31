/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edemairy
 */
public class UnionSet {
    protected int[] pset;
    public UnionSet(int n) {
        pset = new int[n];
        for (int i = 0; i < n; i++) {
            pset[i] = i;
        }
    }
    
    int findSet(int i) {
        int current = i;
        while (current != pset[current]) {
           current = pset[current];
        }
        pset[i] = current;
        return current;
    }
    
    boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }
    
    void unionSet(int i, int j) {
        pset[findSet(i)] = findSet(j);
    }
}
