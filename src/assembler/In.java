package assembler;

public class In extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
