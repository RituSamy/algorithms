package com.company;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        // go through all vertices
        for (int s = 0; s < G.V(); s++) {
            // if one is unmarked, call dfs
            if (!marked[s]) dfs(G, s, s);
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    private void dfs(Graph G, int v, int u) {
        // mark the current vertex
        marked[v] = true;
        // iterate through the adjacency list
        for (int w : G.adj(v)) {
            // if one is not marked, call dfs. search for v starting from w.
            // basically, search if there is a path from v to w
            // that is not just a direct connection... (most recent on the stack).
            if (!marked[w]) dfs(G, w, v);
            // only set hasCycle to true if the path length is > 1. (not a - b, b - a).
            else if (w != u) hasCycle = true;
        }
    }
}
