/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edemairy
 */
public class UnionFind {

    int[] parent;
    int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getRoot(int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    public void union(int x, int y) {
        int x_root = getRoot(x);
        int y_root = getRoot(y);
        if (x_root != y_root) {
            if (size[x_root] < size[y_root]) {
                parent[x_root] = y_root;
                size[y_root] += size[x_root];
            } else { // if (size[x_root] >= size[y_root]) {
                parent[y_root] = x_root;
                size[x_root] += size[y_root];
            }
        }
    }
}
