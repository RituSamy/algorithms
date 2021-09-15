package com.company;

public class Cyclic {
    // we have an original string and a rotated string. We want to know the index in rotated of
    // rightmost occurrence of the first character of original.
    // then we start from that index and check characters, using % M to move across the string.
    int right = -1;
    int M;
    String original, rotated;
    public Cyclic(String original, String rotated) {
        this.original = original;
        this.rotated = rotated;

        M = original.length();
        for (int i = 0; i < M; i++) {
            if (rotated.charAt(i) == original.charAt(0)) right = i;
        }
    }

    public boolean isRotation() {
        if (rotated.length() != M) return false;
        if (right == -1) return false;

        for (int i = right+1, j = 1; i != right && j < M; i = (i+1) % M, j++) {
            if (rotated.charAt(i) != original.charAt(j)) return false;
        }
        return true;
    }
}
