package com.company;
import java.util.Arrays;

public class KMPchar {
    private char[] pat;
    private int[][] dfa;
    Queue<Integer> q;

    public KMPchar(char[] pat) {
        this.pat = pat;
        int M = pat.length;
        int R = 256;
        dfa = new int[R][M];
        q = new Queue<Integer>();
        if (M > 0) dfa[pat[0]][0] = 1;

        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat[j]][j] = j+1;
            X  = dfa[pat[j]][X];
        }

    }

    public int search(char[] txt) {
        int i, j, N = txt.length, M = pat.length;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt[i]][j];
        }
        if (M > 0 && j == M) return i - M;
        else return N;
    }

    public int count(char[] txt) {
        int c = 0, lo = 0, M = pat.length, N;
        while (lo < txt.length) {
            N = txt.length - lo;
            char[] remaining = Arrays.copyOfRange(txt, lo, lo+N);
            int i = search(remaining);
            if (i == N) break;
            c++;
            q.enqueue(lo+i);
            lo += i + M; // update lo to right after the occurrence of the pattern
        }
        return c;
    }

    public void searchAll(char[] txt) {
        count(txt);
        for (int i: q) {
            System.out.println(i);
        }
    }
}
