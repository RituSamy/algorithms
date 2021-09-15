package com.company;

public class UF {
    /*
     * UF(int N) create a new union-find data structure w N objects 0 to N-1
     * void union(int p, int q) create a connection between two objects
     * boolean connected(int p, int q) check if there is a path btwn two objects
     * int find(int p) find the component identifier for p
     * int count() number of components
     *
     * initially, values match indices
     * then, when union, change all values that equal a[p] to equal a[q].
     * to see if connected, simply compare a[p] and a[q].
     * */
    private int[] id;

    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int find(int p) {
        return id[p];
    }

    public int count() {
        return id.length;
    }


}