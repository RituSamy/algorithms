package com.company;

public class FordFulkerson {
    private boolean[] marked; // is s->v path in the residual graph already?
    private FlowEdge[] edgeTo; // last edge on shortest s->v path
    private double value; // maxflow value

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            // compute the bottleneck capacity by starting w max value and
            // decreasing as we go along the path
            double bottle = Double.POSITIVE_INFINITY;
            // we go backwards because to use edgeTo we retrace the path
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }
            // augment flow
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }

    // bfs
    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        // initalize it here bc it needs to reset each time
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> q = new Queue<Integer>();

        marked[s] = true;
        q.enqueue(s);
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e: G.adj(v)) {
                int w = e.other(v);
                // if there is a not full forward edge or not empty backward edge
                if (!marked[w] && e.residualCapacityTo(w) > 0) {
                    marked[w] = true;
                    edgeTo[w] = e;
                    q.enqueue(w);
                }
            }
        }
        // an augmenting path exists if the bfs reached the sink t
        return marked[t];
    }
}
