package com.company;
import java.util.Scanner;

/*
* Write a stack client Parentheses that reads in a text stream from standard input
and uses a stack to determine whether its parentheses are properly balanced.
* For example, your program should print true for [()]{}{[()()]()}
* and false for [(]).
* */
public class Parentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String parentheses = scanner.nextLine();
        char[] p = parentheses.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char c: p) {
            if (stack.isEmpty()) {
                System.out.println("Not balanced.");
                return;
            }
           if (c == ')') {
               if (stack.pop() != '(') {
                   System.out.println("Not balanced.");
                   return;
               }
           } else if (c == ']') {
               if (stack.pop() != '[') {
                   System.out.println("Not balanced.");
                   return;
               }
           } else if (c == '}') {
               if (stack.pop() != '{') {
                   System.out.println("Not balanced.");
                   return;
               }
           } else stack.push(c);
        }
        System.out.println("Balanced.");
        return;
    }
}
