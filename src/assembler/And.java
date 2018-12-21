package assembler;

public class And extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
