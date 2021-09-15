package com.company;
import java.util.Scanner;

public class Evaluate {
    public static void main(String[] args) {
        Stack<String> operators = new Stack<String>();
        Stack<Double> operands = new Stack<Double>();

        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] tokens = s.split(" ");

        for (String token:tokens) {
            if (token.equals("("));
            else if (token.equals("+") || token.equals("*")) {
                operators.push(token);
            }
            else if (token.equals(")")) {
                // pop top two operands and top operator and evaluate
                Double op1 = operands.pop();
                Double op2 = operands.pop();
                String operator = operators.pop();

                if (operator.equals("+")) operands.push(op1 + op2);
                else if (operator.equals("*")) operands.push(op1 * op2);
            }
            else {
                operands.push(Double.parseDouble(token));
            }
        }
        System.out.println(operands.pop());
    }
}
