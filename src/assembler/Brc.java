package assembler;

public class Brc extends Instruction {
  private int i;

  public Brc(int i) {
    this.i = i;
  }

  public int getI() {
    return i;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
