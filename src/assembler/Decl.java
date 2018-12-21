package assembler;

public class Decl extends Instruction {
  private int n;

  public Decl(int n) {
    this.n = n;
  }

  public int getN() {
    return n;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
