package assembler;

public class AsmFormatVisitor implements AsmVisitor {
  private String formattedAsm = "";
  private int lineIndex;

  public String getFormattedAsm() {
    return formattedAsm;
  }

  public void visit(Add c) {
    addLine("ADD\n");
  }

  public void visit(And c) {
    addLine("AND\n");
  }

  public void visit(Brc c) {
    addLine("BRC " + c.getI() + "\n");
  }

  public void visit(Call c) {
    addLine("CALL " + c.getN() + "\n");
  }

  public void visit(Cmp c) {
    addLine("CMP " + c.getOp() + "\n");
  }

  public void visit(Decl c) {
    addLine("DECL " + c.getN() + "\n");
  }

  public void visit(Div c) {
    addLine("DIV\n");
  }

  public void visit(Halt c) {
    addLine("HALT\n");
  }

  public void visit(In c) {
    addLine("IN\n");
  }

  public void visit(Ldi c) {
    addLine("LDI " + c.getC() + "\n");
  }

  public void visit(Lfs c) {
    addLine("LFS " + c.getI() + "\n");
  }

  public void visit(Mod c) {
    addLine("MOD\n");
  }

  public void visit(Mul c) {
    addLine("MUL\n");
  }

  public void visit(Not c) {
    addLine("NOT\n");
  }

  public void visit(Nop c) {
    addLine("NOP\n");
  }

  public void visit(Or c) {
    addLine("OR\n");
  }

  public void visit(Out c) {
    addLine("OUT\n");
  }

  public void visit(Pop c) {
    addLine("POP " + c.getI() + "\n");
  }

  public void visit(Push c) {
    addLine("PUSH " + c.getI() + "\n");
  }

  public void visit(Return c) {
    addLine("RETURN " + c.getN() + "\n");
  }

  public void visit(Sts c) {
    addLine("STS " + c.getI() + "\n");
  }

  public void visit(Sub c) {
    addLine("SUB\n");
  }

  private void addLine(String instruction) {
    formattedAsm += lineIndex++ + ": " + instruction;
  }
}
