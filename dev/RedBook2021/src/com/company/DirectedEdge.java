package com.company;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private int v, w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() { return v; }
    public int to() { return w; }
    public double weight() { return weight; }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    public int compareTo(DirectedEdge e) {
        if (this.weight() < e.weight()) return -1;
        else if (this.weight() > e.weight()) return 1;
        else return 0;
    }
}
