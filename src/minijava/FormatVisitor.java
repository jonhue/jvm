package minijava;

import codegen.*;

public class FormatVisitor implements ProgramVisitor {
  private static int indentationFactor = 2;

  private String formattedCode = "";
  private int indentation;

  public String getFormattedCode() {
    return formattedCode;
  }

  public void visit(Assignment c) {
    formattedCode += indent() + c.getName() + " = ";
    c.getExpression().accept(this);
    formattedCode += ";\n";
  }

  public void visit(Binary c) {
    c.getLhs().accept(this);
    formattedCode += " " + operatorToString(c.getOperator()) + " ";
    c.getRhs().accept(this);
  }

  public void visit(BinaryCondition c) {
    c.getLhs().accept(this);
    formattedCode += " " + operatorToString(c.getOperator()) + " ";
    c.getRhs().accept(this);
  }

  public void visit(Break c) {
    formattedCode += indent() + "break;\n";
  }

  public void visit(Call c) {
    formattedCode += c.getFunctionName() + "(";
    for (int i = 0; i < c.getArguments().length; i++) {
      if (i != 0) formattedCode += ", ";
      c.getArguments()[i].accept(this);
    }
    formattedCode += ")";
  }

  public void visit(Comparison c) {
    c.getLhs().accept(this);
    formattedCode += " " + operatorToString(c.getOperator()) + " ";
    c.getRhs().accept(this);
  }

  public void visit(Composite c) {
    for (Statement statement : c.getStatements())
      statement.accept(this);
  }

  public void visit(Declaration c) {
    formattedCode += indent() + "int " + c.getNames()[0];
    for (int i = 1; i < c.getNames().length; i++)
      formattedCode += ", " + c.getNames()[i];
    formattedCode += ";\n";
  }

  public void visit(ExpressionStatement c) {
    formattedCode += indent();
    c.getExpression().accept(this);
    formattedCode += ";\n";
  }

  public void visit(False c) {
    formattedCode += "false";
  }

  public void visit(Function c) {
    formattedCode += indent() + "int " + c.getName() + "(";
    for (int i = 0; i < c.getParameters().length; i++) {
      if (i != 0) formattedCode += ", ";
      formattedCode += "int " + c.getParameters()[i];
    }
    formattedCode += ") {\n";
    incIndentation();
    for (Declaration declaration : c.getDeclarations())
      declaration.accept(this);
    for (Statement statement : c.getStatements())
      statement.accept(this);
    decIndentation();
    formattedCode += indent() + "}\n";
  }

  public void visit(IfThen c) {
    formattedCode += indent() + "if (";
    c.getCond().accept(this);
    formattedCode += ") {\n";
    incIndentation();
    c.getThenBranch().accept(this);
    decIndentation();
    formattedCode += indent() + "}\n";
  }

  public void visit(IfThenElse c) {
    formattedCode += indent() + "if (";
    c.getCond().accept(this);
    formattedCode += ") {\n";
    incIndentation();
    c.getThenBranch().accept(this);
    decIndentation();
    formattedCode += indent() + "} else {\n";
    incIndentation();
    c.getElseBranch().accept(this);
    decIndentation();
    formattedCode += indent() + "}\n";
  }

  public void visit(Number c) {
    formattedCode += c.getValue();
  }

  public void visit(Program c) {
    for (int i = 0; i < c.getFunctions().length; i++) {
      if (i != 0) formattedCode += "\n";
      c.getFunctions()[i].accept(this);
    }
  }

  public void visit(Read c) {
    formattedCode += "read()";
  }

  public void visit(Return c) {
    formattedCode += indent() + "return ";
    c.getExpression().accept(this);
    formattedCode += ";\n";
  }

  public void visit(Switch c) {
    formattedCode += indent() + "switch(";
    c.getExpression().accept(this);
    formattedCode += ") {\n";
    incIndentation();
    for (SwitchCase switchCase : c.getCases())
      switchCase.accept(this);
    if (c.getDefault() != null) {
      formattedCode += indent() + "default: {\n";
      incIndentation();
      c.getDefault().accept(this);
      decIndentation();
      formattedCode += indent() + "}\n";
    }
    decIndentation();
    formattedCode += indent() + "}\n";
  }

  public void visit(SwitchCase c) {
    formattedCode += indent() + "case " + c.getNumber() + ": {\n";
    incIndentation();
    c.getCaseStatement().accept(this);
    decIndentation();
    formattedCode += indent() + "}\n";
  }

  public void visit(True c) {
    formattedCode += "true";
  }

  public void visit(Unary c) {
    formattedCode += operatorToString(c.getOperator());
    c.getOperand().accept(this);
  }

  public void visit(UnaryCondition c) {
    formattedCode += c.getOperator();
    c.getOperand().accept(this);
  }

  public void visit(Variable c) {
    formattedCode += c.getName();
  }

  public void visit(While c) {
    if (c.isDoWhile()) {
      formattedCode += indent() + "do {\n";
      incIndentation();
      c.getBody().accept(this);
      decIndentation();
      formattedCode += indent() + "} while (";
      c.getCond().accept(this);
      formattedCode += ")\n";
    } else {
      formattedCode += indent() + "while (";
      c.getCond().accept(this);
      formattedCode += ") {\n";
      incIndentation();
      c.getBody().accept(this);
      decIndentation();
      formattedCode += indent() + "}\n";
    }
  }

  public void visit(Write c) {
    formattedCode += "write(";
    c.getExpression().accept(this);
    formattedCode += ")";
  }

  private String operatorToString(Object operator) throws IllegalArgumentException {
    if (operator == Bbinop.And)
      return "&&";
    else if (operator == Bbinop.Or)
      return "||";
    else if (operator == Binop.Minus)
      return "-";
    else if (operator == Binop.Plus)
      return "+";
    else if (operator == Binop.MultiplicationOperator)
      return "*";
    else if (operator == Binop.DivisionOperator)
      return "/";
    else if (operator == Binop.Modulo)
      return "%";
    else if (operator == Bunop.Not)
      return "!";
    else if (operator == Comp.Equals)
      return "==";
    else if (operator == Comp.NotEquals)
      return "!=";
    else if (operator == Comp.LessEqual)
      return "<=";
    else if (operator == Comp.Less)
      return "<";
    else if (operator == Comp.GreaterEqual)
      return ">=";
    else if (operator == Comp.Greater)
      return ">";
    else if (operator == Unop.Minus)
      return "-";
    else
      throw new IllegalArgumentException("The passed operator is invalid.");
  }

  private String indent() {
    String result = "";

    for (int i = 0; i < indentation * indentationFactor; i++)
      result += " ";

    return result;
  }

  private void incIndentation() {
    indentation++ ;
  }

  private void decIndentation() {
    indentation--;
  }
}
