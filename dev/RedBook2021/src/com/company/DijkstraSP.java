package com.company;

public class DijkstraSP {
    private double distTo[];
    private DirectedEdge edgeTo[];
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return edgeTo[v] != null;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    // relax a certain edge
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!pq.contains(w)) { pq.insert(w, distTo[w]); }
                else { pq.decreaseKey(w, distTo[w]); }
            }
        }
    }
}
