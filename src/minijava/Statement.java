package minijava;

public abstract class Statement {
  abstract public void accept(ProgramVisitor visitor);
}
