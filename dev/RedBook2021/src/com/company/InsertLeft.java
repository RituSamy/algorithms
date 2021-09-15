package com.company;

import java.util.Scanner;

public class InsertLeft {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] exp = scanner.nextLine().toCharArray();
        Stack<Character> operands = new Stack<Character>();
        Stack<Character> operators = new Stack<Character>();
        String result = scanner.nextLine();

        for (char c: exp) {
            if (c == '+' || c == '-' || c == '*' || c == '/') operators.push(c);
            if (c == ')') {
                char op1 = operands.pop();
                char op2 = operands.pop();
                char operator = operators.pop();
                result += "(" + op1 + operator + op2 + ")";
            }
            else operands.push(c);
        }
    }
}
