package minijava;

public class False extends Condition {
  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
