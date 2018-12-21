package assembler;

public class Call extends Instruction {
  private int n;

  public Call(int n) {
    this.n = n;
  }

  public int getN() {
    return n;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
