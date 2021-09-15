package com.company;
import sun.swing.text.html.FrameEditorPaneTag;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private long patHash;
    private String pat;
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 256; // radix
    private long RM; // R ^(M-1) % Q;

    public RabinKarp(String pat) {
        this.pat = pat; // only for Las Vegas
        M = pat.length();
        Q = BigInteger.probablePrime(160, new Random()).longValue();
        // exercise 5.3.33: implement longRandomPrime()
        // why are we computing it this way instead of just writing R^(M-1) % Q?
        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }
// s00s m = 4
    public boolean isPalindrome(String pat) {
        int M = pat.length();
        long a = 0, b = 0;
        for (int i = 0; i < M/2; i++) {
            a = ((R * a) + pat.charAt(i)) % Q;
            b = ((R * b) + pat.charAt(M-i-1)) % Q;

        }
        return (a == b);
    }

    // 5.3.12
    private boolean check(String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i)) return false;
            i++;
        }
        return true;
    }

    private boolean wildCheck(String txt, int i) {
        for (int j = 0; j < M; j++) {
            if (pat.charAt(j) != txt.charAt(i) && j != (M-1)/2) return false;
            i++;
        }
        return true;
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = ((R * h) + key.charAt(j)) % Q;
        }
        return h;
    }

    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(txt, 0)) return 0; // if the two are the same

        for (int i = M; i < N; i++) {
            // the extra Q is added to make sure everything stays positive so that the remainder
            // operation works as it should?
            // why the first % Q?
            txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q; // subtract leading digit
            txtHash = (txtHash*R + txt.charAt(i)) % Q; // add next digit
            if (patHash == txtHash) {
                if (check(txt, i - M + 1)) return i - M + 1;
            }
        }
        return N;
    }

    private long wildHash(String key, int M, int d) { // d is the index of the character to be ignored
        long h = 0;
        for (int j = 0; j < M; j++) {
            if (j != d) h = ((R * h) + key.charAt(j)) % Q;
        }
        return h;
    }

    // 5.3.21: middle character is a wild card
    public int wildCardSearch(String txt) {
        int N = txt.length();
        int mid = (M-1)/2;
        long txtHash = wildHash(txt, M, mid); // first M characters, excluding middle
        patHash = wildHash(pat, M, mid); // exclude the middle
        if (patHash == txtHash && wildCheck(txt, 0)) return 0; // if the pattern appears at the beginning

        for (int i = M; i < N; i++) {
            // the extra Q is added to make sure everything stays positive so that the remainder
            // operation works as it should?
            // why the first % Q?
            // shift over by 1, increment mid
            String str = txt.substring(i - M + 1, i+1);
            txtHash = wildHash(str, M, mid);
            if (patHash == txtHash) {
                if (wildCheck(txt, i - M + 1)) return i - M + 1;
            }
        }
        return N;
    }

    public int count(String txt) {
        // c is number of occurrences, lo is starting index for new search
        int c = 0, lo = 0, N;
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
