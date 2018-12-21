package minijava;

public class Program {
  private Function[] functions;

  public Program(Function[] functions) {
    this.functions = functions;
  }

  public Function[] getFunctions() {
    return functions;
  }

  public void accept(ProgramVisitor visitor) {
    visitor.visit(this);
  }
}
