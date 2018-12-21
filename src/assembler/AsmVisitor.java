package assembler;

public interface AsmVisitor {
  void visit(Add c);
  void visit(And c);
  void visit(Brc c);
  void visit(Call c);
  void visit(Cmp c);
  void visit(Decl c);
  void visit(Div c);
  void visit(Halt c);
  void visit(In c);
  void visit(Ldi c);
  void visit(Lfs c);
  void visit(Mod c);
  void visit(Mul c);
  void visit(Nop c);
  void visit(Not c);
  void visit(Or c);
  void visit(Out c);
  void visit(Pop c);
  void visit(Push c);
  void visit(Return c);
  void visit(Sts c);
  void visit(Sub c);
}
