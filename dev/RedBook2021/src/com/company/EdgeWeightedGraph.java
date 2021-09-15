package com.company;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj; // store edges

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    // add the edge to both vertices
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].put(e);
        adj[w].put(e);
        E++;
    }
    // iterable object (bag) for all edges connected to v
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    // return an iterable bag of all the edges
    public Iterable<Edge> edges() {
        Bag<Edge> edges = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e: adj[v]) {
                // only add each edge twice. > is just for convention.
                if (e.other(v) > v) edges.put(e);
            }
        }
        return edges;
    }
}
