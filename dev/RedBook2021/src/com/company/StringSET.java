package com.company;

public class StringSET {

    public StringSET() {

    }
    private TST trie = new TST();

    public void add(String key) {
        trie.put(key, null);
    }

    public void delete(String key) {
        trie.delete(key);
    }

    public boolean contains(String key) {
        return trie.get(key) != null;
    }

    public boolean isEmpty() { return trie.isEmpty(); }

    public int size() {
        int count = 0;
        for (Object s: trie.keys()) {
            count++;
        }
        return count;
    }

    public String toString() {
        String str = "";
        for (Object s: trie.keys()) {
            str += s + ", ";
        }
        return str;
    }
}
