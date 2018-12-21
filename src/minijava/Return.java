package minijava;

public class Return extends Statement {
  private Expression expression;

  public Return(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
