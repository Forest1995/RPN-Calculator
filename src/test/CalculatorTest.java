package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rpn.Calculator;
import rpn.CalculatorException;

class CalculatorTest {
    static Calculator calculator;

    @BeforeAll
    public static void init() {
        calculator =new Calculator();
    }
    @Test
    void TestAddition() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("5 2 +");
        assertEquals(7, calculator.getNumStack().get(0));
        assertEquals(1, calculator.getNumStack().size());
    }

    @Test
    void TestSubtraction() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("5 2 -");
        assertEquals(3, calculator.getNumStack().get(0));
        calculator.parseInput("3 -");
        assertEquals(0, calculator.getNumStack().get(0));
    }

    @Test
    void TestMultiplication() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("1 2 3 4 5");
        calculator.parseInput("* * * *");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(120, calculator.getNumStack().get(0));
    }

    @Test
    void TestDivision() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("7 12 2 /");
        assertEquals(7, calculator.getNumStack().get(0));
        assertEquals(6,  calculator.getNumStack().get(1));
        calculator.parseInput("*");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(42, calculator.getNumStack().get(0));
        calculator.parseInput("4 /");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(10.5, calculator.getNumStack().get(0));
    }

    @Test
    void TestSqrt() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("2 sqrt");
        assertEquals(Math.sqrt(2),calculator.getNumStack().get(0));
        calculator.parseInput("clear 9 sqrt");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(3, calculator.getNumStack().get(0));
    }

    @Test
    void TestUndo() throws CalculatorException {
        calculator.parseInput("clear");
        calculator.parseInput("5 4 3 2");
        assertEquals(4, calculator.getNumStack().size());
        calculator.parseInput("undo undo *");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(20, calculator.getNumStack().get(0));
        calculator.parseInput("5 *");
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(100, calculator.getNumStack().get(0));
        calculator.parseInput("undo");
        assertEquals(2, calculator.getNumStack().size());
        assertEquals(20, calculator.getNumStack().get(0));
        assertEquals(5, calculator.getNumStack().get(1));
    }

    @Test
    void TestClear() throws CalculatorException {
        calculator.parseInput("5 2");
        calculator.parseInput("clear");
        assertEquals(0, calculator.getNumStack().size());
    }

    @Test
    void TestIllegalInput() throws CalculatorException {
        calculator.parseInput("clear");
        try {
            calculator.parseInput("1 2 3 * 5 + * * 6 5");
        } catch (CalculatorException e) {
            assertEquals("operator * (position: 15): insufficient parameters", e.getMessage());
        }
        assertEquals(1, calculator.getNumStack().size());
        assertEquals(11, calculator.getNumStack().get(0));
    }

}