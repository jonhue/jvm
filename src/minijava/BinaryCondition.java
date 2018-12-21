package minijava;

import codegen.Bbinop;

public class BinaryCondition extends Condition {
  private Condition lhs;
  private Bbinop operator;
  private Condition rhs;

  public BinaryCondition(Condition lhs, Bbinop operator, Condition rhs) {
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }

  public Condition getLhs() {
    return lhs;
  }

  public Bbinop getOperator() {
    return operator;
  }

  public Condition getRhs() {
    return rhs;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
