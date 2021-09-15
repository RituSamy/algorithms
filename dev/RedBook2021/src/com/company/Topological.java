package com.company;

public class Topological {
    private Iterable<Integer> order;

    public Topological(EdgeWeightedDigraph G, int s) {
        DirectedCycle directedCycle = new DirectedCycle(G);
        if (!directedCycle.hasCycle()) {
            DepthFirstOrder order = new DepthFirstOrder(G);
            this.order = order.reversePost();
        }
    }

    boolean isDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
