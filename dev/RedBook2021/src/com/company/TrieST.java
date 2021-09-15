package com.company;

public class TrieST<Value> {
    // alphabet
    private static int R = 256;
    private Node root;

    // private class Node with value and array of Nodes
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        private int size; // number of nodes in subtree
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        // if you've reached the end of the string
        if (d == key.length()) return x;

        char c = key.charAt(d);
        // search for the next character
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == 0) {
            x.next[key.charAt(0)].size++;
        }
        // if you've reached the end of the string, set the value
        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        // put the next character in place
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        // maintain a string representing the string of characters leading up
        // to x.
        // if x.val != null, add its string to the queue
        // then visit all the nodes in its array of links, one for each character
        // for loop c = 0 c < R c++
        // the key for each call = append the current character to the prefix
        if (x == null) {
            return;
        }
        if (x.val != null) q.enqueue(pre);

        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    // "s.." will return "she" and "sea"
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    /* pre = "", pat = "s.."
    d = 0, pat.length = 3
    next = pat.charAt(0) = s
    eventually c will equal s, then we collect w x as root.
    pre becomes s.
    c = '.', pre becomes s.. all links will be examined, some will come back
    as null.
    */
    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        // no need to examine characters after the pattern is detected
        if (d == pat.length() && x.val != null) q.enqueue(pre);
        if (d == pat.length()) return;
        
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            // '.' is a wildcard.
            if (next == '.' || next == c) {
                // pre keeps track of the previous string of characters
                collect(x.next[c], pre+c, pat, q);
            }
        }
    }

    // which string in the trie is the longest prefix of s?
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        // lets say "sh" is in the trie and we want longest prefix of "shore".
        // h value not null, so length = 2
        if (x.val != null) length = d;
        // if you happen to find the whole string
        if (d == s.length()) return length;

        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == 0) {
            x.next[key.charAt(0)].size--;
        }
        if (d == key.length()) {
            x.val = null; // essentially delete it
        }
        else {
            char c = key.charAt(d); // s
            x.next[c] = delete(x.next[c], key, d+1);
        }

        // the only two cases where you return x and don't do anything else:
        // if x has no children, set it to null
        // if x has a value or it has children, stop
        if (x.val != null) return x; // "she". e val = 5
        // check if it has any non null children
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        // goes all the way down
        // this will be tossed back up to the line in the else statement
        // and everything up will be set to null
        return null;
    }
}
