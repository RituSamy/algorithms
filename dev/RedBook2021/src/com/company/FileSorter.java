package com.company;
import java.io.File;
import java.util.Arrays;

public class FileSorter {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        File[] files = directory.listFiles();
        Integer[] a = {5, -9, 20, 4, 3, 3, 1, 2};
        Quick.sort(files);
//        Shuffle.shuffle(files);
        Quick.sort(a);
//        Shuffle.shuffle(a);

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
