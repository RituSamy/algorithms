package com.company;

public class DirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean hasCycle;
    private boolean hasNegativeCycle;

    public DirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        // go through all vertices
        for (int s = 0; s < G.V(); s++) {
            // if one is unmarked, call dfs
            if (!marked[s]) dfs(G, s, s);
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    private void dfs(EdgeWeightedDigraph G, int v, int u) {
        // mark the current vertex
        marked[v] = true;
        // iterate through the adjacency list
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            // if one is not marked, call dfs. search for v starting from w.
            // basically, search if there is a path from v to w
            // that is not just a direct connection... (most recent on the stack).
            if (!marked[w]) dfs(G, w, v);
                // only set hasCycle to true if the path length is > 1. (not a - b, b - a).
            // only set hasCycle to true if
            else {
                for (DirectedEdge edge: G.adj(w)) {
                    int x = edge.to();
                    if (x == u) hasCycle = true;
                }
            }
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo != null) spt.addEdge(edgeTo[v]);
        }

        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        hasNegativeCycle = cf.hasCycle();
    }

}
