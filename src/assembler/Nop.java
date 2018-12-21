package assembler;

public class Nop extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
