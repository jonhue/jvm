package assembler;

public class Lfs extends Instruction {
  private int i;

  public Lfs(int i) {
    this.i = i;
  }

  public int getI() {
    return i;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
