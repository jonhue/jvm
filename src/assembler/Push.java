package assembler;

public class Push extends Instruction {
  private int i;

  public Push(int i) throws IllegalArgumentException {
    if (i != 0 && i != 1)
      throw new IllegalArgumentException("i must be one of `0`, `1`.");

    this.i = i;
  }

  public int getI() {
    return i;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
