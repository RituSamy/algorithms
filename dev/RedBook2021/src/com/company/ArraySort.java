package com.company;

public class ArraySort {
    // takes an array of arrays of ints.
    public static void sort(int[][] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(int[][] a, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int pivot = a[lo][d];

        int i = lo + 1;
        while (i <= gt) {
            int t = a[i][d];
            if (t < pivot) exch(a, lt++, i++);
            else if (t > pivot) exch(a, gt--, i);
            else i++;
        }

        sort(a, lo, lt-1, d);
        // the middle keys all have the same first character, so move to the next
        if (pivot >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    public static void exch(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
