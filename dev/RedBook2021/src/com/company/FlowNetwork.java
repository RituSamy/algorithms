package com.company;

public class FlowNetwork {
    private int V, E;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Bag<FlowEdge>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(int v, int w, double cap) {
        FlowEdge e = new FlowEdge(v, w, cap);
        adj[v].put(e);
        adj[w].put(e);
        E++;
    }

    public Iterable<FlowEdge> adj(int v) { return adj[v]; }
    public Iterable<FlowEdge> edges() {
        Stack<FlowEdge> edges = new Stack<FlowEdge>();
        for (int i = 0; i < adj.length; i++) {
            for (FlowEdge e: adj[i]) {
                edges.push(e);
            }
        }
        return edges;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < adj.length; v++) {
            s += v + ": ";
            for (FlowEdge e : adj[v]) {
                s += e.toString() + ", ";
            }
            s += "\n";
        }
        return s;
    }
}
