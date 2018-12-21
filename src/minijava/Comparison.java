package minijava;

import codegen.Comp;

public class Comparison extends Condition {
  private Expression lhs;
  private Comp operator;
  private Expression rhs;

  public Comparison(Expression lhs, Comp operator, Expression rhs) {
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }

  public Expression getLhs() {
    return lhs;
  }

  public Comp getOperator() {
    return operator;
  }

  public Expression getRhs() {
    return rhs;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
