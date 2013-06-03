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
    protected int[] size;
    public UnionSet(int n) {
        pset = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            pset[i] = i;
            size[i] = 1;
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
    
    int getSize(int i) {
        return size[findSet(i)];
    }
    
    int[] getSizes() {
        return size;
    }
    
    void unionSet(int i, int j) {
        int fsi = findSet(i);
        int fsj = findSet(j);
        pset[fsi] = fsj;
        if (fsi != fsj) {
            size[fsj] += size[fsi];
        }
    }
}
