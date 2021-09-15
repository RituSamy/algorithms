package com.company;
import java.util.Comparator;

public class Insertion {
    public static void sort(Object[] a, Comparator c) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && isLess(c, a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; (j > lo && isLess(a[j], a[j-1], d)); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // substring is faster than charAt(). why?
    public static boolean isLess(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    public static boolean isLess(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
