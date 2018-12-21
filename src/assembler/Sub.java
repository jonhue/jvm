package assembler;

public class Sub extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
