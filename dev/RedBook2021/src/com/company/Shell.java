package com.company;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        // find the largest starting value
        while (h < N / 3) h = 3*h + 1;

        while (h >= 1) {
            // insertion sort by h increments
            for (int i = 0; i < N; i++) {
                // j has to be >= h to avoid index out of bounds
                for (int j = i; j >= h && Helpers.isLess(a[j], a[j-1]); j-= h) {
                    Helpers.exch(a, j, j-1);
                }
            }
            h = h / 3;
        }
    }
}
