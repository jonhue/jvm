package minijava;

public class Write extends Expression {
  private Expression expression;

  public Write(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
