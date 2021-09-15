package com.company;
import java.io.*;

public class BinaryDump {
    public static void main(String[] args) {
        int width = Integer.parseInt(args[0]);
        int cnt;
        for (cnt = 0; !BinaryStdIn.isEmpty(); cnt++)
        {
            if (width == 0) continue;
            if (cnt != 0 && cnt % width == 0)
                System.out.println();
            if (BinaryStdIn.readBoolean())
                System.out.print("1");
            else System.out.print("0");
        }
        System.out.println();
        System.out.println(cnt + " bits");
    }
}

