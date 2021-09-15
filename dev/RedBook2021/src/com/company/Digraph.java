package com.company;

public class Digraph {
    private int V; // num vertices why did i get an error here when I created the new constructor?
    private int E; // num edges
    Bag<Integer>[] adj; // adjacency list

    public Digraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    // 4.1.3: copy constructor
    public Digraph(Graph G) {
        Bag<Integer>[] adj = (Bag<Integer>[]) new Bag[G.V()];
        for (int v = 0; v < G.V(); v++) {
            for (int w: G.adj(v)) {
                adj[v].put(w);
            }
        }
    }

    public int V() { return V; }
    public int E() { return E; }

    // 4.1.5: modified to disallow parallel edges and self-loops
    public void addEdge(int v, int w) {
        if (v == w) return;
        for (int s: adj[v]) {
            if (s == w) return; // if it already exists
        }

        adj[v].put(w);
        E++;
    }
    // 4.1.4: hasEdge()
    public boolean hasEdge(int v, int w) {
        for (int s: adj[v]) {
            if (s == w) return true;
        }
        return false;
    }

    // to access adj[]
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w: adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w: adj[v]) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
