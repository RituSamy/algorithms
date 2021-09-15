package com.company;

public class LazyPrimMST {
    // to keep track of vertices connecting from the T to outside of the T
    private boolean[] marked; // have we visited v yet?
    private Queue<Edge> mst; // the actual mst: a queue of edges
    private MinPQ<Edge> minEdges; // priority queue to get the next minimum edge
    private double weight; // total weight of the mst

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        minEdges = new MinPQ<Edge>(G.E());

        visit(G, 0);
        while (!minEdges.isEmpty()) {
            Edge e = minEdges.delMin(); // get the min edge
            int v = e.either(), w = e.other(v);
            // if both vertices are already marked
            if (marked[v] && marked[w]) continue;
            // if you haven't yet seen this edge (and its the min so far),
            // it must be in the MST.
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }

    }

    // separating this code out into another function
    private void visit(EdgeWeightedGraph G, int v) {
        for (Edge e: G.adj(v)) { // iterate through all adjacent edges
            int w = e.other(v);
            // if the vertex on the other end is already marked, the edge is ineligible.
            if (!marked[w]) {
                minEdges.insert(e); // insert the edge in the PQ
            }
        }
    }

    public double weight() {
        return weight;
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
