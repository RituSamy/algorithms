package com.company;

// return the connected components
public class CC {
    private int[] id;
    private boolean[] marked;
    private int count;

    public CC(Graph G) {
        id = new int[G.V()];
        marked = new boolean[G.V()];
        cc(G);
    }

    public int numComponents() {
        return count;
    }

    private void cc(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w: G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }
}
