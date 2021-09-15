package com.company;

public class BellmanFordSP {
    private double distTo[];
    private DirectedEdge edgeTo[];

    private Queue<Integer> queue; // vertices being relaxed
    private boolean[] onQ; // is this vertex currently being relaxed?

    private Iterable<DirectedEdge> cycle; // negative cycle in edgeTo[]
    private int cost; // number of calls to relax()

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        queue = new Queue<Integer>();
        onQ = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;

        // remove vertex from queue and relax it
        while (!queue.isEmpty() && !this.hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(G, v);
        }

    }

    public boolean hasNegativeCycle() { return cycle != null; }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
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

                // enqueue adjacent vertices
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
            // after i passes, i vertices are relaxed. If you increment cost and it equals v
            // check for a negative cycle every Vth call to relax.
            if (cost++ % G.V() == 0) { findNegativeCycle(); }
        }
    }

    // if the shortest path tree has a cycle, then there must be a negative cycle.
    // otherwise a cycle would increase the total weight.
    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V); // shortest path tree

        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) spt.addEdge(edgeTo[v]); // add all the edges to spt.
        }

        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();
    }
}
