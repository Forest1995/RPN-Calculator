package rpn;

import java.util.HashMap;
import static java.lang.Math.sqrt;

public enum Operator {
    ADDITION("+", OperatorType.TWO_OPERANDS) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            return secondOperand + firstOperand;
        }
    },

    SUBTRACTION("-", OperatorType.TWO_OPERANDS) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand - firstOperand;
        }
    },

    MULTIPLICATION("*", OperatorType.TWO_OPERANDS) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand * firstOperand;
        }
    },

    DIVISION("/", OperatorType.TWO_OPERANDS) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            if (firstOperand == 0)
                throw new CalculatorException("Cannot divide by 0.");
            return secondOperand / firstOperand;
        }
    },

    SQUAREROOT("sqrt", OperatorType.ONE_OPERAND) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return sqrt(firstOperand);
        }
    },

    UNDO("undo", OperatorType.NONE_OPERAND) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("Invalid operation");
        }
    },

    CLEAR("clear", OperatorType.NONE_OPERAND) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("Invalid operation");
        }
    };

    public abstract Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException;

    private String symbol;
    private OperatorType operatorType;

    Operator(String symbol, OperatorType operatorType) {
        this.symbol = symbol;
        this.operatorType = operatorType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    private static final HashMap<String, Operator> operatorMap = new HashMap<String, Operator>();

    static {
        for (Operator o : values()) {
            operatorMap.put(o.getSymbol(), o);
        }
    }

    public static Operator getOperator(String symbol) {
        return operatorMap.get(symbol);
    }

}
