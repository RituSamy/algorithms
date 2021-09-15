package com.company;

public class BoyerMoore {
    private String pat;
    private int[] right;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int R = 256;
        int M = pat.length();
        right = new int[R];
        for (int c = 0; c < R; c++) right[c] = -1;
        // if c is the last char in the pattern, set right[c] to the penultimate occurrence of c
        int pen = M;
        for (int j = 0; j < M-1; j++) {
            if (pat.charAt(j) == pat.charAt(M)) pen = j; // if there is a repeat of the last character earlier
            right[pat.charAt(j)] = j;
        }
        right[pat.charAt(M)] = pen;
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;

        if (M == 0) return N;

        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                char c = txt.charAt(i + j);
                if (pat.charAt(j) != c) { // if there is a mismatch
                    skip = Math.max(j - right[c], 1); // cuz j - -1 = j + 1. (no need to explicitly write this, it works mathematically).
                    break;
                }
            }
            if (skip == 0) return i; // found, because skip was never changed in the for loop.
        }
        return N; // not found;
    }

    public int count(String txt) {
        // c is number of occurrences, lo is starting index for new search
        int c = 0, lo = 0, M = pat.length(), N;
        while (lo < txt.length()) { // until you reach the end of the text
            N = txt.substring(lo).length();
            int i = search(txt.substring(lo)); // starting index of the substring
            if (i == N) break;
            c++; // if it exists, increment c
            lo += i + M; // update lo to right after the occurrence of the pattern
        }
        return c;
    }
}
