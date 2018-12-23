package minijava;

import codegen.*;

public class FormatTest {
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
  }

  private static void renderMiniJava(Program program) {
    FormatVisitor formatVisitor = new FormatVisitor();
    program.accept(formatVisitor);
    System.out.println(formatVisitor.getFormattedCode());
  }
}
