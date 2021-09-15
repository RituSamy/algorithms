package com.company;
import static com.company.Helpers.*;

public class Merge {
    private static final int CUTOFF = 7;

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
//        if (hi <= lo + CUTOFF - 1) { // cutoff is num elements
//            Insertion.sort(a);
//            return;
//        }

        int mid = lo + (hi-lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        if (!isLess(a[mid+1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
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
