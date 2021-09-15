package com.company;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;
    /*
     * create a MinPQ to sort the edges by weight and access the lowest weight one
     * create a queue to store edges in the MST
     * create a UF for the connected components
     *
     * iterate through the edges in G:
     *   check if two are not in the same component. if so,
     *   add to the queue
     */
    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> minHeap = new MinPQ<Edge>(G.E());
        for (Edge e: G.edges()) {
            minHeap.insert(e);
        }
        mst = new Queue<Edge>();
        UF cc = new UF(G.V());

        // mst cannot surpass V-1
        while (!minHeap.isEmpty() && mst.size() < G.V() - 1) {
            Edge edge = minHeap.delMin();
            int v = edge.either();
            int w = edge.other(v);
            // if they're not in the same cc, then a cycle won't form.
            if (!cc.connected(v, w)) {
                cc.union(v, w); // connect them
                mst.enqueue(edge); // push the edge onto the queue
                weight += edge.weight();
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
