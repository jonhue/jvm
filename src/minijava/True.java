package minijava;

public class True extends Condition {
  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
