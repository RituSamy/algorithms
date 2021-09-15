package com.company;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj; // store edges

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    // add the edge to only one vertices
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adj[v].put(e);
        E++;
    }
    // iterable object (bag) for all edges connected to v
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }
    // return an iterable bag of all the edges
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e: adj[v]) {
                edges.put(e);
            }
        }
        return edges;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (DirectedEdge e: adj[v]) {
                int w = e.to();
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
