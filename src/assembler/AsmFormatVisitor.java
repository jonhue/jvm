package assembler;

public class AsmFormatVisitor implements AsmVisitor {
  private String formattedAsm = "";
  private int lineIndex;

  public String getFormattedAsm() {
    return formattedAsm;
  }

  public void visit(Add c) {
    formattedAsm += getLineIndex() + "ADD\n";
  }

  public void visit(And c) {
    formattedAsm += getLineIndex() + "AND\n";
  }

  public void visit(Brc c) {
    formattedAsm += getLineIndex() + "BRC " + c.getI() + "\n";
  }

  public void visit(Call c) {
    formattedAsm += getLineIndex() + "CALL " + c.getN() + "\n";
  }

  public void visit(Cmp c) {
    formattedAsm += getLineIndex() + "CMP " + c.getOp() + "\n";
  }

  public void visit(Decl c) {
    formattedAsm += getLineIndex() + "DECL " + c.getN() + "\n";
  }

  public void visit(Div c) {
    formattedAsm += getLineIndex() + "DIV\n";
  }

  public void visit(Halt c) {
    formattedAsm += getLineIndex() + "HALT\n";
  }

  public void visit(In c) {
    formattedAsm += getLineIndex() + "IN\n";
  }

  public void visit(Ldi c) {
    formattedAsm += getLineIndex() + "LDI " + c.getC() + "\n";
  }

  public void visit(Lfs c) {
    formattedAsm += getLineIndex() + "LFS " + c.getI() + "\n";
  }

  public void visit(Mod c) {
    formattedAsm += getLineIndex() + "MOD\n";
  }

  public void visit(Mul c) {
    formattedAsm += getLineIndex() + "MUL\n";
  }

  public void visit(Not c) {
    formattedAsm += getLineIndex() + "NOT\n";
  }

  public void visit(Nop c) {
    formattedAsm += getLineIndex() + "NOP\n";
  }

  public void visit(Or c) {
    formattedAsm += getLineIndex() + "OR\n";
  }

  public void visit(Out c) {
    formattedAsm += getLineIndex() + "OUT\n";
  }

  public void visit(Pop c) {
    formattedAsm += getLineIndex() + "POP " + c.getI() + "\n";
  }

  public void visit(Push c) {
    formattedAsm += getLineIndex() + "PUSH " + c.getI() + "\n";
  }

  public void visit(Return c) {
    formattedAsm += getLineIndex() + "RETURN " + c.getN() + "\n";
  }

  public void visit(Sts c) {
    formattedAsm += getLineIndex() + "STS " + c.getI() + "\n";
  }

  public void visit(Sub c) {
    formattedAsm += getLineIndex() + "SUB\n";
  }

  private String getLineIndex() {
    return lineIndex++ + ": ";
  }
}
