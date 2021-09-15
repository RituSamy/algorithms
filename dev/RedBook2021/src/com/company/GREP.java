package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GREP {
    public static void main(String[] args) {
        String regexp = args[0];
        System.out.println(regexp);
        NFA nfa = new NFA(regexp);

        File file = new File("tinyL.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String txt = scanner.next();
                if (nfa.recognizes(txt)) System.out.println(txt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
