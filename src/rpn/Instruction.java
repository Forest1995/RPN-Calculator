package rpn;

public class Instruction {
    private Operator operator;
    private Double firstOperand;
    private Double secondOperand;

    Instruction(Operator operator, Double firstOperand, Double secondOperand){
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }
    Instruction(Operator operator, Double firstOperand){
        this.firstOperand = firstOperand;
        this.operator = operator;
        this.secondOperand = null;
    }

    Instruction(Double firstOperand){
        this.firstOperand = firstOperand;
        this.operator = null;
        this.secondOperand = null;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(Double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public Double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(Double secondOperand) {
        this.secondOperand = secondOperand;
    }
}
