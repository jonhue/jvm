package assembler;

public class Not extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
