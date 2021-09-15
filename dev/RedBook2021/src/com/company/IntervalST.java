package com.company;

public class IntervalST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int count;
        Key max;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;

    public void put(Key lo, Key hi, Value val) {

    }

//    public Value get(Key lo) { // no two intervals have the same lo
//        if (lo == null) return null;
//
//    }

//    public Key intersect(Key lo, Key hi) {
//        Huffman x = root;
//        while (x != null) {
//
//        }
//    }




}
