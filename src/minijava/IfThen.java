package minijava;

public class IfThen extends Statement {
  private Condition cond;
  private Statement thenBranch;

  public IfThen(Condition cond, Statement thenBranch) {
    this.cond = cond;
    this.thenBranch = thenBranch;
  }

  public Condition getCond() {
    return cond;
  }

  public Statement getThenBranch() {
    return thenBranch;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
