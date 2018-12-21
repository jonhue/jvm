package minijava;

import assembler.*;
import codegen.*;

public class SwitchTest {
  public static void main(String[] args) {
    Function main1 = new Function("main",
            new String[] {},
            new Declaration[] {new Declaration(new String[] {"x", "y", "z"})},
            new Statement[] {
                    new Assignment("x", new Read()),
                    new Assignment("z", new Number(0)),
                    new Switch(new Variable("x"), new SwitchCase[] {
                            new SwitchCase(1, new Composite(new Statement[] {
                                    new Assignment("y", new Read()),
                                    new Switch(new Variable("y"), new SwitchCase[] {
                                            new SwitchCase(1, new Composite(new Statement[] {
                                                    new Assignment("z", new Binary(new Variable("z"), Binop.Plus, new Number(1))),
                                                    new Break()
                                            })),
                                            new SwitchCase(2, new Composite(new Statement[] {
                                                    new Assignment("z", new Binary(new Variable("z"), Binop.Plus, new Number(2)))
                                            }))
                                    }, null),
                                    new Break()
                            })),
                            new SwitchCase(2, new Composite(new Statement[] {
                                    new Assignment("z", new Number(42))
                            }))
                    }, new Assignment("z", new Binary(new Variable("z"), Binop.Plus, new Number(1)))),
                    new ExpressionStatement(new Write(new Variable("z"))),
                    new Return(new Number(0))
            });

    Program program1 = new Program(new Function[] {main1});

    FormatVisitor formatVisitor1 = new FormatVisitor();
    program1.accept(formatVisitor1);
    System.out.println(formatVisitor1.getFormattedCode());

    System.out.println("---");

    CodeGenerationVisitor codeGenerationVisitor1 = new CodeGenerationVisitor();
    program1.accept(codeGenerationVisitor1);
    Instruction[] instructions1 = codeGenerationVisitor1.getProgram();

    AsmFormatVisitor asmFormatVisitor1 = new AsmFormatVisitor();
    for (Instruction instruction : instructions1)
      instruction.accept(asmFormatVisitor1);
    System.out.println(asmFormatVisitor1.getFormattedAsm());

    System.out.println("---");

    Interpreter interpreter1 = new Interpreter(instructions1);
    interpreter1.execute();
  }
}
