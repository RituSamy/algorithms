package com.company;
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        private Node next;
    }

    private Node first;
    private int size;

    public void put(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
    }

    public boolean contains(Item item) {
        Node curr = first;
        while (curr != null) {
            if (curr.item == item) return true;
            curr = curr.next;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Item first() {
        return first.item;
    }

//    public boolean contains(Item item) {
//        Huffman x = first;
//        while (x != null) {
//            if (x.item == item) return true;
//        }
//        return false;
//    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

    // Return the current item and MOVE to the next item.
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {};
    }

    public static class AcyclicSP {
        /*
        * first compute topological order.
        * iterate through vertices in this order and relax all adjacent edges.
        * */
        private double distTo[];
        private DirectedEdge edgeTo[];

        public AcyclicSP(EdgeWeightedDigraph G, int s) {
            distTo = new double[G.V()];
            edgeTo = new DirectedEdge[G.V()];
            for (int v = 0; v < G.V(); v++) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }
            distTo[s] = 0.0;

            Topological topological = new Topological(G,s);
            for (int v: topological.order()) {
                relax(G, v);
            }
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
                }
            }
        }
    }
}
