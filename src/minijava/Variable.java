package minijava;

public class Variable extends Expression {
  private String name;

  public Variable(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
