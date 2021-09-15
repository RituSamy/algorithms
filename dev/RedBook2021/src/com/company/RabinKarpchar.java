package com.company;
import java.math.BigInteger;
import java.util.Random;

public class RabinKarpchar {
    private long patHash;
    private char[] pat;
    private int M; // pattern length
    private long Q; // a large prime
    private int R = 256; // radix
    private long RM; // R ^(M-1) % Q;

    public RabinKarpchar(char[] pat) {
        this.pat = pat;
        M = pat.length;
        // idk what is going on here
        Q = BigInteger.probablePrime(160, new Random()).longValue();
        while (Q < 0) Q = BigInteger.probablePrime(160, new Random()).longValue();

        RM = 1;
        for (int i = 1; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        patHash = hash(pat, M);
    }

    private boolean check(char[] txt, int i) {
        for (int j = 0; j < M; j++) {
            if (pat[j] != txt[i]) return false;
            i++;
        }
        return true;
    }

    private long hash(char[] key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++) {
            h = ((R * h) + key[j]) % Q;
        }
        return h;
    }

    public int search(char[] txt) {
        int N = txt.length;
        long txtHash = hash(txt, M);
        if (patHash == txtHash && check(txt, 0)) return 0;

        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM*txt[i-M] % Q) % Q;
            txtHash = (txtHash*R + txt[i]) % Q;
            if (patHash == txtHash) {
                if (check(txt, i - M + 1)) return i - M + 1;
            }
        }
        return N;
    }
}
