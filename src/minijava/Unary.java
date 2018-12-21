package minijava;

import codegen.Unop;

public class Unary extends Expression {
  private Unop operator;
  private Expression operand;

  public Unary(Unop operator, Expression operand) {
    this.operator = operator;
    this.operand = operand;
  }

  public Unop getOperator() {
    return operator;
  }

  public Expression getOperand() {
    return operand;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
