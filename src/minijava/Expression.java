package minijava;

public abstract class Expression {
  abstract public void accept(ProgramVisitor visitor);
}
