package minijava;

public class Switch extends Statement {
  private Expression expression;
  private SwitchCase[] cases;
  private Statement defaultCase;

  public Switch(Expression expression, SwitchCase[] cases, Statement defaultCase) {
    this.expression = expression;
    this.cases = cases;
    this.defaultCase = defaultCase;
  }

  public Expression getExpression() {
    return expression;
  }

  public SwitchCase[] getCases() {
    return cases;
  }

  public Statement getDefault() {
    return defaultCase;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
