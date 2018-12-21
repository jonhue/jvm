package minijava;

public class Assignment extends Statement {
  private String name;
  private Expression expression;

  public Assignment(String name, Expression expression) {
    this.name = name;
    this.expression = expression;
  }

  public String getName() {
    return name;
  }

  public Expression getExpression() {
    return expression;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
