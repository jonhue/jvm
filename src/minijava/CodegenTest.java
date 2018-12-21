package minijava;

import assembler.*;
import codegen.*;

public class CodegenTest {
  public static void main(String[] args) {
    Function main1 = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("sum", new Expression[]
                            {new Read(), new Read()}))),
                    new Return(new Number(0))
            });

    Function sum1 = new Function("sum",
            new String[] {"a", "b"},
            new Declaration[] {},
            new Statement[] {
                    new Return(new Binary(new Variable("a"), Binop.Plus, new Variable("b")))
            });

    Program program1 = new Program(new Function[] {sum1, main1});

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

    System.out.println("\n===\n");

    Function main2 = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("fak", new Expression[] {new Read()}))),
                    new Return(new Number(0))
            });

    Function fak2 = new Function("fak",
            new String[] {"n"},
            new Declaration[] {},
            new Statement[] {
                    new IfThen(new Comparison(new Variable("n"), Comp.Equals, new Number(1)), new Return(new Number(1))),
                    new Return(new Binary(new Variable("n"), Binop.MultiplicationOperator, new Call("fak", new Expression[] {new Binary(new Variable("n"), Binop.Minus, new Number(1))})))
            });

    Program program2 = new Program(new Function[] {fak2, main2});

    FormatVisitor formatVisitor2 = new FormatVisitor();
    program2.accept(formatVisitor2);
    System.out.println(formatVisitor2.getFormattedCode());

    System.out.println("---");

    CodeGenerationVisitor codeGenerationVisitor2 = new CodeGenerationVisitor();
    program2.accept(codeGenerationVisitor2);
    Instruction[] instructions2 = codeGenerationVisitor2.getProgram();

    AsmFormatVisitor asmFormatVisitor2 = new AsmFormatVisitor();
    for (Instruction instruction : instructions2)
      instruction.accept(asmFormatVisitor2);
    System.out.println(asmFormatVisitor2.getFormattedAsm());

    System.out.println("---");

    Interpreter interpreter2 = new Interpreter(instructions2);
    interpreter2.execute();

    System.out.println("\n===\n");

    Function main3 = new Function("main",
            new String[] {},
            new Declaration[] {},
            new Statement[] {
                    new ExpressionStatement(new Write(new Call("ggt", new Expression[] {new Read(), new Read()}))),
                    new Return(new Number(0))
            });

    Function ggt3 = new Function("ggt",
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

    Program program3 = new Program(new Function[] {ggt3, main3});

    FormatVisitor formatVisitor3 = new FormatVisitor();
    program3.accept(formatVisitor3);
    System.out.println(formatVisitor3.getFormattedCode());

    System.out.println("---");

    CodeGenerationVisitor codeGenerationVisitor3 = new CodeGenerationVisitor();
    program3.accept(codeGenerationVisitor3);
    Instruction[] instructions3 = codeGenerationVisitor3.getProgram();

    AsmFormatVisitor asmFormatVisitor3 = new AsmFormatVisitor();
    for (Instruction instruction : instructions3)
      instruction.accept(asmFormatVisitor3);
    System.out.println(asmFormatVisitor3.getFormattedAsm());

    System.out.println("---");

    Interpreter interpreter3 = new Interpreter(instructions3);
    interpreter3.execute();
  }
}
