package assembler;

public class Mod extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
