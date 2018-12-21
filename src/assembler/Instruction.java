package assembler;

public abstract class Instruction {
  abstract public void accept(AsmVisitor visitor);
}
