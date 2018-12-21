package assembler;

public class Ldi extends Instruction {
  private int c;

  public Ldi(int c) {
    this.c = c;
  }

  public int getC() {
    return c;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
