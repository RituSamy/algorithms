package com.company;
// memory: 8N + 88 ~8N bytes
public class QuickUnionUF { // 16 bytes object overhead, 4 padding
    private int[] id; // 24 overhead + 4N (4 bytes per int) + 8 ref
    private int[] sz; // 4N + 24 + 8 ref
    private int count; // 4

    public QuickUnionUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            // make every other node point to grandparent (not exactly pointing to root)
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    // weighted quick union: link root of smaller tree to link of larger tree.
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else { id[j] = i; sz[i] += sz[j]; }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int p) {
        return id[p];
    }

    public int count() {
        return count;
    }
}
