package com.company;

public class DirectedDFS {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DirectedDFS(Digraph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int s: sources) {
            dfs(G, s);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v))
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    public boolean marked(int v) { return marked[v]; }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
