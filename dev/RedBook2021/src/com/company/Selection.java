package com.company;

public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            // find the min
            for (int j = i+1; j < N; j++) {
                if (Helpers.isLess(a[j], a[min])) min = j;
            }
            Helpers.exch(a, i, min);
        }
    }
}
