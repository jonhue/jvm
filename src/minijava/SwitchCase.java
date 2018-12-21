package minijava;

public class SwitchCase {
  private int number;
  private Statement caseStatement;

  public SwitchCase(int number, Statement caseStatement) {
    this.number = number;
    this.caseStatement = caseStatement;
  }

  public int getNumber() {
    return number;
  }

  public Statement getCaseStatement() {
    return caseStatement;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
