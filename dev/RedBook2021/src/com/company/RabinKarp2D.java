package com.company;
import java.math.BigInteger;
import java.util.Random;

public class RabinKarp2D {
    private long patHash;
    private char[][] pat;
    private int H, V; // pat dimensions
    private long Q; // a large prime
    private int R = 256; // radix
    private long RM; // R ^(M-1) % Q;

    public RabinKarp2D(char[][] pat) {
        this.pat = pat;
        H = pat.length;
        V = pat[0].length;
        Q = BigInteger.probablePrime(160, new Random()).longValue();
        while (Q < 0) Q = BigInteger.probablePrime(160, new Random()).longValue();

        RM = 1;
        for (int r = 1; r < H; r++) {
            for (int c = 1; c < V; c++) {
                RM = (R * RM) % Q;
            }
        }
        patHash = hash(pat, H, V);
    }
    // returns the row in which it is found.
    // bug: doesn't actually line up starting positions.
//    public int[] search(char[][] txt) {
//        int N = txt.length;
//        if (txt.length != txt[0].length) return new int[] {N, N};
//        if (N < H || N < V) return new int[] {N, N};
//
//        int i, j;
//        for (i = 0, j = 0; i < N && j < H; i++) {
//            RabinKarpchar rk = new RabinKarpchar(pat[j]);
//            if (rk.search(txt[i]) < N) j++;
//            else j = 0;
//        }
//        if (j == H) return new int[] {i-H, N-V};
//        else return new int[] {N, N};
//    }

    // hash by column
    private long hash(char[][] key, int H, int V) {
        long h = 0;

        for (int c = 0; c < V; c++) {
            for (int r = 0; r < H; r++) {
                h = ((R * h) + key[r][c]) % Q;
            }
        }

        return h;
    }

    private boolean check(char[][] txt, int r, int c) {
        return true;
    }

    public int[] search(char[][] txt) {
        int N = txt.length;
        int j;
        long txtHash = hash(txt, H, V);
        if (patHash == txtHash && check(txt, 0, 0)) return new int[] {0, 0};

        for (int k = 0; k < H; k++) {
            j = V; // j = last column of current "search rectangle".
            // scan the kth row
            while (j < N) {
                for (int i = k; i < k+H; i++) {
                    // subtract txt[i][j - V].
                    txtHash = (txtHash + Q - RM*txt[i][j-V] % Q) % Q; // subtract V leading digits
                }
                for (int i = k; i < k+H; i++) {
                    // add txt[i][j].
                    txtHash = (txtHash*R + txt[i][j]) % Q;
                }
                if (patHash == txtHash) {
                    if (check(txt, k, j-V)) return new int[] {k, j-V};
                }
                j++;
            }
        }
        return new int[] {N, N};
    }

//    public int[] search(char[][] txt) {
//        int N = txt.length;
//        long txtHash = hash(txt, H, V);
//        if (patHash == txtHash && check(txt, 0)) return new int[]{0, 0};
//
//        for (int r = H-1; r < N; r++) {
//            for (int c = V-1; c < N-1; c++) {
//                for (int i = r-H+1; i <= r; i++) {
//                    char deleting = txt[i][c - (V-1)];
//                    // subtract leading column
//                    txtHash = (txtHash + Q - RM*txt[i][c-V+1] % Q) % Q;
//                }
//                for (int i = r-H+1; i <= r; i++) {
//                    char adding = txt[i][c+1];
//                    // add trailing column
//                    txtHash = (txtHash*R + txt[i][c+1]) % Q;
//                }
//
//                if (patHash == txtHash) {
//                    int[] index = {r-H+1, c-V+1};
//                    return index;
//                }
//
//            }
//        }
//
//        return new int[]{N, N};
//    }

}
