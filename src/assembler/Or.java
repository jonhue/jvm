package assembler;

public class Or extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
