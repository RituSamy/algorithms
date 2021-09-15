package com.company;

import static com.company.Helpers.isLess;
import static com.company.Helpers.isSorted;

public class BottomUpMerge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) {
            for (int lo = 0; lo < N-sz; lo += sz+sz) {
                // there might not be enough elements left at the end
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) {
            // copy everything into the aux array, so that you can return a[].
            aux[k] = a[k];
        }

        // pointers to the beginnings of the two halves
        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++]; // if i passes middle
            else if (j > hi) a[k] = aux[i++]; // if j passes end
            else if (isLess(aux[j], aux[i])) a[k] = aux[j++]; // comp values
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }

}
