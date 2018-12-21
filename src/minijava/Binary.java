package minijava;

import codegen.Binop;

public class Binary extends Expression {
  private Expression lhs;
  private Binop operator;
  private Expression rhs;

  public Binary(Expression lhs, Binop operator, Expression rhs) {
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }

  public Expression getLhs() {
    return lhs;
  }

  public Binop getOperator() {
    return operator;
  }

  public Expression getRhs() {
    return rhs;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
