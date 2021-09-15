package com.company;

public class Helpers {
    public static boolean isLess(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi-1; i++) {
            for (int j = i+1; j < hi; j++) {
                if (isLess(a[j], a[i])) return false;
            }
        }
        return true;
    }
}
