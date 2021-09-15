package com.company;
import java.util.Random;

public class Shuffle {
    public static void shuffle(Comparable[] a) {
        int N = a.length;
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            int r = random.nextInt(i+1);
            Helpers.exch(a, i, r);
        }
    }
}
