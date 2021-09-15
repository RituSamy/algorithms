package com.company;

public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Object val;
        int size;
    }

    public boolean isEmpty() { return root == null; }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        else return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // doesn't handle empty strings properly.
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length()-1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (key.length() != 0) {
            char c = key.charAt(d);
            if (x == null) { x = new Node(); x.c = c;}
            if (c < x.c) x.left = put(x.left, key, val, d);
            else if (c > x.c) x.right = put(x.right, key, val, d);
            else if (d < key.length()-1) {
                x.mid = put(x.mid, key, val, d+1);
            }
            else x.val = val;
        }
        return x;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        else {
            char c = key.charAt(d);
            if (c < x.c) x.left = delete(x.left, key, d);
            else if (c > x.c) x.right = delete(x.right, key, d);
            else if (d < key.length()-1) {
                x.mid = delete(x.mid, key, d+1);
            }
            else x.val = null;
        }

        if (x.val != null) return x;
        return null;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        inOrderTraversal(get(root, pre, 0), pre, q);
        return q;
    }

    private Iterable<String> inOrderTraversal(Node x, String pre, Queue<String> q) {
        if (x == null) return null;

        if (x.val != null) { // enqueue the whole string once the value is reached.
            q.enqueue(pre + x.c); // [ she, shells]
        }

        // if we're still in the middle of a string...
        inOrderTraversal(x.left, pre, q);
        inOrderTraversal(x.mid, pre + x.c, q);
        inOrderTraversal(x.right, pre, q);

        return q;
    }
}
