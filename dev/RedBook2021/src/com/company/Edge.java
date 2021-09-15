package com.company;

public class Edge implements Comparable<Edge> {
    private final int v, w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    double weight() { return weight; }

    public int either() { return v; }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("inconsistent edge");
    }

    public int compareTo(Edge e) {
        if (this.weight() < e.weight()) return -1;
        else if (this.weight() > e.weight()) return 1;
        else return 0;
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

}
