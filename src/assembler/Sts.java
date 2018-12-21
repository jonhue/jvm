package assembler;

public class Sts extends Instruction {
  private int i;

  public Sts(int i) {
    this.i = i;
  }

  public int getI() {
    return i;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
