package com.company;

public class Quick3String {
    public static void sort(String[] a) {
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int pivot = charAt(a[lo], d);

        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < pivot) exch(a, lt++, i++);
            else if (t > pivot) exch(a, gt--, i);
            else i++;
        }

        sort(a, lo, lt-1, d);
        // the middle keys all have the same first character, so move to the next
        if (pivot >= 0) sort(a, lt, gt, d+1);
        sort(a, gt+1, hi, d);
    }

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    // substring is faster than charAt(). why?
    public static boolean isLess(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
