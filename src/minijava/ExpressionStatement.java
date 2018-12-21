package minijava;

public class ExpressionStatement extends Statement {
  private Expression expression;

  public ExpressionStatement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
