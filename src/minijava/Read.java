package minijava;

public class Read extends Expression {
  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
