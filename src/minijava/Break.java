package minijava;

public class Break extends Statement {
  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
