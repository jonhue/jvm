package assembler;

public class Mul extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
