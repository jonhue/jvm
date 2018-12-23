package assembler;

public class Cmp extends Instruction {
  private String op;

  public Cmp(String op) throws IllegalArgumentException {
    if (op != "EQUALS" && op != "LESS")
      throw new IllegalArgumentException("op must be one of `EQUALS`, `LESS`.");

    this.op = op;
  }

  public String getOp() {
    return op;
  }

  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
