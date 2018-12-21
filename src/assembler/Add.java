package assembler;

public class Add extends Instruction {
  public void accept(AsmVisitor visitor) {
      visitor.visit(this);
    }
}
