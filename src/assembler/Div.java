package assembler;

public class Div extends Instruction {
  public void accept(AsmVisitor visitor) {
    visitor.visit(this);
  }
}
