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

    AsmFormatVisitor asmFormatVisitor1 = new AsmFormatVisitor();
    for (Instruction instruction : instructions1)
      instruction.accept(asmFormatVisitor1);
    System.out.println(asmFormatVisitor1.getFormattedAsm());

    System.out.println("---");

    System.out.println("Fakultät:");
    Interpreter interpreter1 = new Interpreter(instructions1);
    interpreter1.execute();

    System.out.println("\n===\n");

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

    AsmFormatVisitor asmFormatVisitor2 = new AsmFormatVisitor();
    for (Instruction instruction : instructions2)
      instruction.accept(asmFormatVisitor2);
    System.out.println(asmFormatVisitor2.getFormattedAsm());

    System.out.println("---");

    System.out.println("GGT:");
    Interpreter interpreter2 = new Interpreter(instructions2);
    interpreter2.execute();
  }
}
