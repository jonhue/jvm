package assembler;

public class InstructionOverflowException extends InterpreterException {
  public InstructionOverflowException(String msg) {
    super(msg);
  }
}

