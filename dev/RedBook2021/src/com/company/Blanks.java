package com.company;

public class Blanks {
    private String txt;
    private String pat = "";
    public Blanks(String txt, int M) {
        this.txt = txt;
        for (int i = 0; i < M; i++) {
            pat += " ";
        }
    }
    public int consecutiveBlanks() {
        BoyerMoore bm = new BoyerMoore(pat);
        return bm.search(txt);
    }
}
