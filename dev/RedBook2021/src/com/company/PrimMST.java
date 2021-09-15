package com.company;

public class PrimMST {
    // have we visited this vertex?
    private boolean[] marked;
    // what is the current shortest known distance to this vertex?
    private double[] distTo;
    // what is the last edge on the path that will take us to this vertex?
    private Edge[] edgeTo;
    // indexed pq
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        // initially, distTo[] of everything must be positive infinity.
        // this is because we need to add the edge to the tree when we haven't
        // yet seen the vertex.
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    // to process a vertex v, we are going to skip ineligible edges AND
    // only add the lowest weight edge
    // If w is already on the tree, v → w is ineligible. skip.
    // If w is not already on the tree, add it to the tree.
    // If the weight of v → w is less than distTo[] of w, we have found a faster way to get to w.
    // Thus, w’s priority in the priority queue must be decreased to reflect this weight.
    // This, decreaseKey(), is why we need an indexminpq.
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e: G.adj(v)) { // iterate through all adjacent edges
            int w = e.other(v);
            // if the vertex on the other end is already marked, the edge is ineligible.
            if (marked[w]) { continue; }
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) { pq.insert(w, distTo[w]); }
                // if w is already in the pq, decrease the key of w (which is distTo[w]).
                else { pq.decreaseKey(w, distTo[w]); }
            }
        }
    }
}
