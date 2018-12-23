package assembler;

public class InterpreterTest {
  public static void main(String[] args) {
    Instruction[] instructions1 = {
            new In(),
            new Ldi(6),
            new Call(1),
            new Out(),
            new Ldi(0),
            new Halt(),
            new Ldi(1),
            new Lfs(0),
            new Cmp("EQUALS"),
            new Not(),
            new Brc(13),
            new Ldi(1),
            new Return(1),
            new Ldi(1),
            new Lfs(0),
            new Sub(),
            new Ldi(6),
            new Call(1),
            new Lfs(0),
            new Mul(),
            new Return(1)
    };
    renderAsm(instructions1);
    System.out.println("Fakultät:");
    interpret(instructions1);

    System.out.println("---");

    Instruction[] instructions2 = {
            new In(),
            new In(),
            new Ldi(7),
            new Call(2),
            new Out(),
            new Ldi(0),
            new Halt(),
            new Decl(1),
            new Lfs(-1),
            new Lfs(0),
            new Cmp("LESS"),
            new Brc(16),
            new Lfs(0),
            new Lfs(-1),
            new Sts(0),
            new Sts(-1),
            new Lfs(0),
            new Sts(1),
            new Lfs(0),
            new Lfs(-1),
            new Mod(),
            new Sts(0),
            new Lfs(1),
            new Sts(-1),
            new Ldi(0),
            new Lfs(0),
            new Cmp("EQUALS"),
            new Not(),
            new Brc(16),
            new Lfs(-1),
            new Return(3)
    };
    renderAsm(instructions2);
    System.out.println("GGT:");
    interpret(instructions2);
  }

  private static void renderAsm(Instruction[] instructions) {
    AsmFormatVisitor asmFormatVisitor = new AsmFormatVisitor();
    for (Instruction instruction : instructions)
      instruction.accept(asmFormatVisitor);
    System.out.println(asmFormatVisitor.getFormattedAsm());
  }

  private static void interpret(Instruction[] instructions) {
    Interpreter interpreter = new Interpreter(instructions);
    interpreter.execute();
  }
}
