package com.company;
import static com.company.Helpers.*;

public class Quick {
    public static void main(String[] args) {
        Integer[] a = {5, 0, 9, 11, 2};
        partition(a, 0, 4);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void sort(Comparable[] a) {
        Shuffle.shuffle(a);
        sortThreeWay(a,0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return; // array size 1
        int j = partition(a, lo, hi); // partition
        sort(a, lo, j-1); // sort left
        sort(a, j+1, hi); // sort right
    }

    public static void sortThreeWay(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        Comparable v = a[lo];
        int lt = lo, gt = hi, i = lo;

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i);
            else i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
          int i = lo, j = hi+1;

          while (true) {
              // increment/decrement before array access
              // keep incrementing while a[i] < pivot and a[j] > pivot. check if it reaches end
              // if they have crossed, break and switch j because you're done
              // if not, stop and exchange i and j.
              while (isLess(a[++i], a[lo])) if (i == hi) break;
              while (isLess(a[lo], a[--j])) if (j == lo) break; // redundant test, partitioning element is at a[lo]
              if (i >= j) break;
              exch(a, i, j);
          }
          exch(a, lo, j);
          return j; // index of item now known to be in place
    }
}
