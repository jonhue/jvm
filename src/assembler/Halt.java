package assembler;

public class Halt extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
