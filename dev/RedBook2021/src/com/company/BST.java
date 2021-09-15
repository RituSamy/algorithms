package com.company;

public class BST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int count;
        boolean color;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            color = RED;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        else return x.color == RED;
    }

    private Node root;

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        // case 1: right-leaning red link. rotateLeft
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        // case 2: two reds in a row
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        // case 3: two red children
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null; // how could it be null?
        return x.key;
    }

    private Node floor(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        else {
            Node t = floor(x.right, key);
            if (t != null) return t;
            else return x;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x.right;
        x = min(x.left);
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        // first search for it
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key); // search for the key
        if (cmp > 0) x.right = delete(x.right, key);
        else { // if you've found it,
            // if no children, this will just be null so x will be replaced w null.
            if (x.right == null) return x.left;
            // if there is one child, that will replace x
            if (x.left == null) return x.right;

            // if two children, replace with the successor.
            Node t = x;
            x = min(t.right); // find the min in the right subtree
            x.right = deleteMin(t.right); // delete the min in the right subtree, assign that to the left of x
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

}
