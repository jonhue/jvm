package minijava;

public interface ProgramVisitor {
  void visit(Assignment c);
  void visit(Binary c);
  void visit(BinaryCondition c);
  void visit(Break c);
  void visit(Call c);
  void visit(Comparison c);
  void visit(Composite c);
  void visit(Declaration c);
  void visit(ExpressionStatement c);
  void visit(False c);
  void visit(Function c);
  void visit(IfThen c);
  void visit(IfThenElse c);
  void visit(Number c);
  void visit(Program c);
  void visit(Read c);
  void visit(Return c);
  void visit(Switch c);
  void visit(SwitchCase c);
  void visit(True c);
  void visit(Unary c);
  void visit(UnaryCondition c);
  void visit(Variable c);
  void visit(While c);
  void visit(Write c);
}
