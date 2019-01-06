package minijava;

import assembler.*;
import codegen.*;

public class CodegenTest {
  public static void main(String[] args) {
    Function main = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("sum", new Expression[]
                            {new Read(), new Read()}))),
                    new Return(new Number(0))
            });

    Function sum = new Function("sum",
            new String[] {"a", "b"},
            new Declaration[] {},
            new Statement[] {
                    new Return(new Binary(new Variable("a"), Binop.Plus, new Variable("b")))
            });

    Program program = new Program(new Function[] {sum, main});

    renderMiniJava(program);
    Instruction[] instructions = compile(program);
    renderAsm(instructions);
    interpret(instructions);

    System.out.println("---");

    main = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("sub", new Expression[]
                            {new Read(), new Read()}))),
                    new Return(new Number(0))
            });

    Function sub = new Function("sub",
            new String[] {"a", "b"},
            new Declaration[] {},
            new Statement[] {
                    new Return(new Binary(new Variable("a"), Binop.Minus, new Variable("b")))
            });

    program = new Program(new Function[] {sub, main});

    renderMiniJava(program);
    instructions = compile(program);
    renderAsm(instructions);
    interpret(instructions);

    System.out.println("---");

    main = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("fak", new Expression[] {new Read()}))),
                    new Return(new Number(0))
            });

    Function fak = new Function("fak",
            new String[] {"n"},
            new Declaration[] {},
            new Statement[] {
                    new IfThen(new Comparison(new Variable("n"), Comp.Equals, new Number(1)), new Return(new Number(1))),
                    new Return(new Binary(new Variable("n"), Binop.MultiplicationOperator, new Call("fak", new Expression[] {new Binary(new Variable("n"), Binop.Minus, new Number(1))})))
            });

    program = new Program(new Function[] {fak, main});

    renderMiniJava(program);
    instructions = compile(program);
    renderAsm(instructions);
    interpret(instructions);

    System.out.println("---");

    main = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("ggt", new Expression[] {new Read(), new Read()}))),
                    new Return(new Number(0))
            });

    Function ggt = new Function("ggt",
            new String[] {"a", "b"},
            new Declaration[] {new Declaration(new String[] {"temp"})},
            new Statement[] {
                    new IfThen(new Comparison(new Variable("b"), Comp.GreaterEqual, new Variable("a")), new Composite(new Statement[] {
                            new Assignment("temp", new Variable("b")),
                            new Assignment("b", new Variable("a")),
                            new Assignment("a", new Variable("temp"))
                    })),
                    new While(new Comparison(new Variable("b"), Comp.NotEquals, new Number(0)), new Composite(new Statement[] {
                            new Assignment("temp", new Variable("b")),
                            new Assignment("b", new Binary(new Variable("a"), Binop.Modulo, new Variable("b"))),
                            new Assignment("a", new Variable("temp"))
                    }), true),
                    new Return(new Variable("a"))
            });

    program = new Program(new Function[] {ggt, main});

    renderMiniJava(program);
    instructions = compile(program);
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
