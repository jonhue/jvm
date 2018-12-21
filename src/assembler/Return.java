package assembler;

public class Return extends Instruction {
  private int n;

  public Return(int n) {
    this.n = n;
  }

  public int getN() {
    return n;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
