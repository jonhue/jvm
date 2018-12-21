package assembler;

public class Out extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
