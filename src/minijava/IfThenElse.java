package minijava;

public class IfThenElse extends Statement {
  private Condition cond;
  private Statement thenBranch;
  private Statement elseBranch;

  public IfThenElse(Condition cond, Statement thenBranch, Statement elseBranch) {
    this.cond = cond;
    this.thenBranch = thenBranch;
    this.elseBranch = elseBranch;
  }

  public Condition getCond() {
    return cond;
  }

  public Statement getThenBranch() {
    return thenBranch;
  }

  public Statement getElseBranch() {
    return elseBranch;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
