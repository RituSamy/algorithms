package com.company;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
//        System.out.println("Enter characters of string.");
//        Scanner scanner = new Scanner(System.in);
//        String input = new String();
//        RabinKarp rk = new RabinKarp("slils");
//        String next = scanner.next();
//        while (next != "done") {
//            input += next;
//            System.out.println(rk.isPalindrome(input));
//        }

        Cyclic cyclic = new Cyclic("example", "ampleex");
        //System.out.println(cyclic.isRotation());

        char[][] text = {
                {'a', 'y', 'e', 'l', 'a'},
                {'a', 'l', 'o', 'w', 'a'},
                {'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a'},
        };

        char[][] pat = {
                {'y', 'e', 'l'},
                {'l', 'o', 'w'}
        };

        RabinKarp2D rk = new RabinKarp2D(pat);
        int[] index = rk.search(text);
        System.out.println("found pattern at " + index[0] + " , " + index[1]);
    }
}
