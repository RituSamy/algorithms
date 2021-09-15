package com.company;

import java.util.Arrays;

public class LSD {
    public static void sort(String[] a, int W) { // sort the leading W characters
        int N = a.length;
        int R = 256; // length of ASCII alphabet
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            int[] count = new int[R+1]; // account for all possible characters

            // find frequencies
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // convert to array positions
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }

            // put the keys in the correct index positions in aux[]
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }

        }
    }

    public String lrs(String s) {
        int N = s.length();
        // generate suffixes
        String[] suff = new String[N];

        for (int i = 0; i < N; i++) {
            suff[i] = s.substring(i, N);
        }

        Arrays.sort(suff);
        String lrs = "";
        for (int i = 0; i < N-1; i++) {
            int len = lcp(suff[i], suff[i+1]);
            if (len > lrs.length()) {
                lrs = suff[i].substring(0, len);
            }
        }
        return lrs;
    }

    // longest common prefix
    private int lcp(String s, String t) {
        int pre = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) break;
            pre++;
        }
        return pre;
    }
}
