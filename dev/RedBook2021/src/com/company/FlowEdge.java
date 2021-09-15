package com.company;

public class FlowEdge {
    private int v, w;
    private double flow;
    private final double capacity;
    // all flows are initially 0
    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int from() { return v; }
    public int to() { return w; }
    public double capacity() { return capacity; }
    public double flow() { return flow; }

    public int other(int x) {
        if (x == v) return w;
        else if (x == w) return v;
        else throw new RuntimeException("inconsistent edge");
    }

    public double residualCapacityTo(int v) {
        if (v == this.v) return flow;
        else if (v == this.w) return capacity - flow;
        else throw new RuntimeException("inconsistent edge");
    }

    public void addResidualFlowTo(int v, double delta) {
        if (v == this.v) flow -= delta;
        else if (v == this.w) flow += delta;
        else throw new RuntimeException("inconsistent edge");
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }
}
