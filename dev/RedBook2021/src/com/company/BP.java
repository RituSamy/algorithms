package com.company;

public class BP {
    private boolean[] marked;
    private boolean[] color;
    private boolean isBipartite = true;

    public BP(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) dfs(G, i);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;

        for (int w: G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }
            else if (color[w] == color[v]) isBipartite = false;
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }
}
