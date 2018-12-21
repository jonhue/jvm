package minijava;

public class Number extends Expression {
  private int value;

  public Number(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
