package com.company;

public class MSD {
    private static int R = 256; // length of ASCII alphabet
    private static String[] aux; // auxiliary array
    private static final int M = 15; // cutoff for small subarrays

    // if all elements of the string have been examined, return -1 for charAt()
    // because d is out of bounds.
    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    // d is the dth character. start at 1st, then 2nd, etc.
    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        // switch to Insertion sort for small subarrays
        if (hi - lo <= M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        // 0 represents a string is finished being examined
        int[] count = new int[R+2];

        // find frequencies. add 2 this time.
        for (int i = lo; i <= hi; i++) {
            count[a[i].charAt(d) + 2]++;
        }

        // convert to array positions. go until R+1
        for (int r = 0; r < R+1; r++) {
            count[r+1] += count[r];
        }

        // put the keys in the correct index positions in aux[]. add 1
        for (int i = lo; i <= hi; i++) {
            aux[count[a[i].charAt(d) + 1]++] = a[i];
        }

        // subtract lo because we are only copying back the subarray starting from lo.
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }

        // recursively sort the remaining subarrays.
        // go from the end of the current subarray to the beginning of the next one.
        // move to the next character d+1.
        for (int r = 0; r < R+1; r++) {
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1);
        }
    }
}
