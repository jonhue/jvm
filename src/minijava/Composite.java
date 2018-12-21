package minijava;

public class Composite extends Statement {
  private Statement[] statements;

  public Composite(Statement[] statements) {
    this.statements = statements;
  }

  public Statement[] getStatements() {
    return statements;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
