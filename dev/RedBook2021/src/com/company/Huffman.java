package com.company;

public class Huffman {
    private static int R = 256;

    private static class Node implements Comparable<Node> {
        private char c;
        private int freq;
        private Node left, right;

        public Node(char c, int freq, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.freq = freq;
            this.c = c;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void compress() {
        /*
■ Use the codeword table to write the codeword for each input character.
        * */

        // Read the input.
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        // Tabulate the frequency of occurrence of each char value in the input.
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        // Build the Huffman encoding trie corresponding to those frequencies.
        Node root = buildTrie(freq);
        // Build the corresponding codeword table, to associate a bitstring with each char
        // value in the input.
        String[] st = new String[R];
        buildCode(st, root, "");
        // Write the trie, encoded as a bitstring.
        writeTrie(root);
        // Write the count of characters in the input, encoded as a bitstring.
        BinaryStdOut.write(input.length);
        // Use the codeword table to write the codeword for each input character.
        for (int i = 0; i < input.length; i++) {
            String code = st[i];
            for (int j = 0; j < code.length(); j++) {
                if (j == '1') BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
            }
        }
        BinaryStdOut.close();
    }



    public static void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();

        for (int i = 0; i < N; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                if (BinaryStdIn.readBoolean()) x = x.right;
                else x = x.left;
            }
            BinaryStdOut.write(x.c);
        }
        BinaryStdOut.close();
    }

    public static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<Node>(freq.length);
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) pq.insert(new Node(c, freq[c], null, null));
        }

        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();

            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin(); // root of final one tree
    }

    public static Node readTrie() {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        } else {
            return new Node('\0', 0, readTrie(), readTrie());
        }
    }

    // recursive
    public static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.c);
        } else {
            BinaryStdOut.write(false);
            writeTrie(x.left);
            writeTrie(x.right);
        }
    }

    private static String[] buildCode(Node root)
    { // Make a lookup table from trie.
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }
    private static void buildCode(String[] st, Node x, String s)
    { // Make a lookup table from trie (recursive).
        if (x.isLeaf())
        { st[x.c] = s; return; } // assign ST value
        // internal node: add binary digit to the code
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }
}
