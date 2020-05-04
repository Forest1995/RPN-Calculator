package rpn;

import java.util.Stack;

public class Calculator {
    private Stack<Double> numStack;
    private Stack<Instruction> instructionStack;
    private int currentTokenIndex;

    public Calculator() {
        numStack = new Stack<>();
        instructionStack = new Stack<>();
        currentTokenIndex = 0;
    }

    /**
     * @param input user input for calculation
     * @throws CalculatorException
     */

    public void parseInput(String input) throws CalculatorException {
        if (input == null) {
            throw new CalculatorException("Input is null.");
        }
        currentTokenIndex = 0;
        String[] tokens = input.split("\\s");
        for (String token : tokens) {
            currentTokenIndex++;
            processToken(token);
        }
    }


    /**
     * @param token input token
     * @return value if it is a number, otherwise null
     */
    private Double tryParseDouble(String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }


    /**
     * @param token input token
     * @throws CalculatorException
     */
    private void processToken(String token) throws CalculatorException {
        Double value = tryParseDouble(token);
        if (value == null) {
            processOperator(token);
        } else {
            numStack.push(Double.parseDouble(token));
            instructionStack.push(new Instruction(value));
        }
    }


    /**
     * @param symbol input operator
     * @throws CalculatorException
     */
    private void processOperator(String symbol) throws CalculatorException {

        Operator operator = Operator.getOperator(symbol);
        if (operator == null) {
            throw new CalculatorException("Error: invalid operator");
        }

        if (operator == Operator.CLEAR) {
            clearStacks();
            return;
        }
        if (numStack.isEmpty()) {
            throw new CalculatorException("Error: empty stack");
        }

        if (operator == Operator.UNDO) {
            undoLastInstruction();
            return;
        }

        if (operator.getOperatorType() == OperatorType.TWO_OPERANDS && numStack.size() < 2) {
            throwInvalidOperand(symbol);
            return;
        }

        Double firstOperand = numStack.pop();
        Double secondOperand = (operator.getOperatorType() == OperatorType.TWO_OPERANDS) ? numStack.pop() : null;

        Double result = operator.calculate(firstOperand, secondOperand);
        if (result == null) {
            throw new CalculatorException("rpn.Calculator error!");
        }
        numStack.push(result);
        if (secondOperand == null) {
            instructionStack.push(new Instruction(operator, firstOperand));
        } else {
            instructionStack.push(new Instruction(operator, firstOperand, secondOperand));
        }
    }

    /**
     * @throws CalculatorException
     */
    private void undoLastInstruction() throws CalculatorException {
        if (instructionStack.isEmpty()) {
            throw new CalculatorException("Error: no operations to undo");
        }
        Instruction lastInstruction = instructionStack.pop();
        numStack.pop();
        if (lastInstruction.getOperator() == null) {
            // Do nothing
        } else if (lastInstruction.getSecondOperand() == null) {
            numStack.push(lastInstruction.getFirstOperand());
        } else {
            numStack.push(lastInstruction.getSecondOperand());
            numStack.push(lastInstruction.getFirstOperand());
        }
    }

    /**
     *
     */
    private void clearStacks() {
        numStack.clear();
        instructionStack.clear();
    }

    /**
     * @param operator
     * @throws CalculatorException
     */
    private void throwInvalidOperand(String operator) throws CalculatorException {
        throw new CalculatorException(
                String.format("operator %s (position: %d): insufficient parameters", operator, currentTokenIndex * 2 - 1));
    }

    public Stack<Double> getNumStack() {
        return numStack;
    }
}
