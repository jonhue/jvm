package minijava;

import codegen.Bunop;

public class UnaryCondition extends Condition {
  private Bunop operator;
  private Condition operand;

  public UnaryCondition(Bunop operator, Condition operand) {
    this.operator = operator;
    this.operand = operand;
  }

  public Bunop getOperator() {
    return operator;
  }

  public Condition getOperand() {
    return operand;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
