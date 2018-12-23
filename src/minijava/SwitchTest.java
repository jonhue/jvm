package minijava;

import assembler.*;
import codegen.*;

public class SwitchTest {
  public static void main(String[] args) {
    Function main = new Function("main",
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

    Program program = new Program(new Function[] {main});

    renderMiniJava(program);
    Instruction[] instructions = compile(program);
    renderAsm(instructions);
    interpret(instructions);
  }

  private static void renderMiniJava(Program program) {
    FormatVisitor formatVisitor = new FormatVisitor();
    program.accept(formatVisitor);
    System.out.println(formatVisitor.getFormattedCode());
  }

  private static Instruction[] compile(Program program) {
    CodeGenerationVisitor codeGenerationVisitor = new CodeGenerationVisitor();
    program.accept(codeGenerationVisitor);
    return codeGenerationVisitor.getProgram();
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
