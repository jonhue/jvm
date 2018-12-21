package minijava;

public class While extends Statement {
  private Condition cond;
  private Statement body;
  private boolean isDoWhile;

  public While(Condition cond, Statement body, boolean isDoWhile) {
    this.cond = cond;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }

  public Condition getCond() {
    return cond;
  }

  public Statement getBody() {
    return body;
  }

  public boolean isDoWhile() {
    return isDoWhile;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
