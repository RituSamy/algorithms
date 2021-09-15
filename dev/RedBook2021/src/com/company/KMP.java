package com.company;

public class KMP {
    private String pat;
    private int[][] dfa;
    Queue<String> q;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        q = new Queue<String>();
        if (M > 0) dfa[pat.charAt(0)][0] = 1;
        // invariant: X < j
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                // if a mismatch occurs, you would behave as if you were in state X. (full backup simulation)
                // thus, copy X's column into j's column to reflect the correct transitions.
                dfa[c][j] = dfa[c][X];
            }
            // after copying, however, one cell must be different. and that is the cell for a
            // match transition. this means you see the correct character in state j. thus, you
            // increment j to j+1.
            dfa[pat.charAt(j)][j] = j+1;
            // update X: if you see this character in state X (hypothetically), what would the new X be?
            X  = dfa[pat.charAt(j)][X];
        }
    }

    public int tandemRepeatSearch(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        // i never decrements, only j moves around based on the dfa value.
        // we are seeing the character at i and we are in state j. thus, dfa[txt.charAt(i)][j].
        int k = 0;
        for (i = 0, j = 0; i < N && j < M; i++) {
            int temp = j;
            j = dfa[txt.charAt(i)][j];
            k = dfa[txt.charAt(i+M)][temp];
        }

        if (M > 0 && j == M && k == M) return i - M;
        else return N; // end of text reached, no match found, return N.
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        // i never decrements, only j moves around based on the dfa value.
        // we are seeing the character at i and we are in state j. thus, dfa[txt.charAt(i)][j] is the state.
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (M > 0 && j == M) return i - M; // i - M gives us the STARTING position of the pattern in the text.
        else return N; // end of text reached, no match found, return N.
    }

    public int count(String txt) {
        // c is number of occurrences, lo is starting index for new search
        int c = 0, lo = 0, M = pat.length(), N;

        while (lo < txt.length()) { // until you reach the end of the text
            N = txt.substring(lo).length(); // length of current search area
            int i = search(txt.substring(lo)); // starting index of the substring
            if (i == N) break; // if it doesn't exist, break
            c++; // if it exists, increment c.
            q.enqueue(txt.substring(lo + i, lo + i + M)); // store the occurrence in q
            lo += i + M; // update lo to right after the occurrence of the pattern for the new search area
        }
        return c;
    }

    public void searchAll(String txt) {
        count(txt);
        for (String s: q) {
            System.out.println(s);
        }
    }
}
