package rpn;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        Calculator calculator = new Calculator();
        System.out.println("Enter your expression, or 'exit' to quit");
        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = console.readLine();
            if ("exit".equals(inputString)) {
                keepRunning = false;
            } else {
                try {
                    calculator.parseInput(inputString);
                } catch (CalculatorException e) {
                    System.out.println(e.getMessage());
                }
                DecimalFormat fmt = new DecimalFormat("0.##########");
                Stack<Double> stack = calculator.getNumStack();
                System.out.print("Stack: ");
                for (Double value : stack) {
                    System.out.print(fmt.format(value));
                    System.out.print(" ");
                }
                System.out.printf("%n");
            }
        }
    }
}