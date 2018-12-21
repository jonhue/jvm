package minijava;

import assembler.*;
import codegen.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CodeGenerationVisitor implements ProgramVisitor {
  private Function currentFunction;
  private HashMap<String, Integer> variableIndices;
  private HashMap<String, Integer> functionIndices = new HashMap<>();
  private HashMap<Integer, String> functionCallStack = new HashMap<>();
  private ArrayList<Integer> breakIndices;
  private Instruction[] program = new Instruction[0];

  public Instruction[] getProgram() {
    return program;
  }

  public void visit(Assignment c) {
    c.getExpression().accept(this);
    addInstruction(new Sts(variableIndices.get(c.getName())));
  }

  public void visit(Binary c) {
    c.getRhs().accept(this);
    c.getLhs().accept(this);
    addOperatorInstruction(c.getOperator());
  }

  public void visit(BinaryCondition c) {
    c.getRhs().accept(this);
    c.getLhs().accept(this);
    addOperatorInstruction(c.getOperator());
  }

  public void visit(Break c) {
    breakIndices.add(program.length);
    addInstruction(new Nop());
  }

  public void visit(Call c) {
    for (Expression expression : c.getArguments())
      expression.accept(this);

    /* Enter dummy NOP to be later replaced by a push to the stack of the line number of the called function */
    functionCallStack.put(program.length, c.getFunctionName());
    addInstruction(new Nop());

    addInstruction(new assembler.Call(c.getArguments().length));
  }

  public void visit(Comparison c) {
    c.getRhs().accept(this);
    c.getLhs().accept(this);
    addOperatorInstruction(c.getOperator());
  }

  public void visit(Composite c) {
    for (Statement statement : c.getStatements())
      statement.accept(this);
  }

  public void visit(Declaration c) {
    for (String var : c.getNames())
      variableIndices.put(var, variableIndices.size() + 1 - currentFunction.getParameters().length);

    addInstruction(new Decl(c.getNames().length));
  }

  public void visit(ExpressionStatement c) {
    c.getExpression().accept(this);
  }

  public void visit(False c) {
    addInstruction(new Ldi(0));
  }

  public void visit(Function c) {
    currentFunction = c;
    functionIndices.put(c.getName(), program.length);

    variableIndices = new HashMap<>();
    for (int i = 0; i < c.getParameters().length; i++)
      variableIndices.put(c.getParameters()[i], i + 1 - c.getParameters().length);

    for (Declaration declaration : c.getDeclarations())
      declaration.accept(this);
    for (Statement statement : c.getStatements())
      statement.accept(this);
  }

  public void visit(IfThen c) {
    c.getCond().accept(this);
    addInstruction(new Not());
    int endIndex = program.length;
    addInstruction(new Nop());
    c.getThenBranch().accept(this);
    program[endIndex] = new Brc(program.length);
  }

  public void visit(IfThenElse c) {
    c.getCond().accept(this);
    addInstruction(new Not());
    int elseIndex = program.length;
    addInstruction(new Nop());
    c.getThenBranch().accept(this);
    int endIndex = program.length;
    addInstruction(new Nop());
    program[elseIndex] = new Brc(program.length);
    c.getElseBranch().accept(this);
    program[endIndex] = new Brc(program.length);
  }

  public void visit(Number c) {
    addInstruction(new Ldi(c.getValue()));
  }

  public void visit(Program c) {
    mainFunction(c).accept(this);

    for (Function function : c.getFunctions())
      if (!function.getName().equals("main")) function.accept(this);

    /* Replace dummy NOPs by LDIs to push the line number of the function to be called onto the stack */
    for (HashMap.Entry<Integer, String> functionCall : functionCallStack.entrySet())
      program[functionCall.getKey()] = new Ldi(functionIndices.get(functionCall.getValue()));
  }

  public void visit(Read c) {
    addInstruction(new In());
  }

  public void visit(Return c) {
    c.getExpression().accept(this);

    if (!currentFunction.getName().equals("main"))
      addInstruction(new assembler.Return(currentFunction.getParameters().length + numOfLocalVariables()));
    else
      addInstruction(new Halt());
  }

  public void visit(Switch c) {
    breakIndices = new ArrayList<>();
    c.getExpression().accept(this);
    addInstruction(new Pop(0));

    for (SwitchCase switchCase : c.getCases())
      switchCase.accept(this);

    if (c.getDefault() != null) c.getDefault().accept(this);

    // replace dummy NOPs with jumps to end of switch statement
    for (Integer index : breakIndices)
      program[index] = new Brc(program.length);
  }

  public void visit(SwitchCase c) {
    addInstruction(new Push(0));
    addInstruction(new Ldi(c.getNumber()));
    addInstruction(new Cmp("EQUALS"));
    int nextCaseIndex = program.length;
    addInstruction(new Nop());
    c.getCaseStatement().accept(this);
    program[nextCaseIndex] = new Brc(program.length);
  }

  public void visit(True c) {
    addInstruction(new Ldi(-1));
  }

  public void visit(Unary c) {
    c.getOperand().accept(this);
    addOperatorInstruction(c.getOperator());
  }

  public void visit(UnaryCondition c) {
    c.getOperand().accept(this);
    addOperatorInstruction(c.getOperator());
  }

  public void visit(Variable c) {
    addInstruction(new Lfs(variableIndices.get(c.getName())));
  }

  public void visit(While c) {
    if (c.isDoWhile()) {
      int bodyIndex = program.length;
      c.getBody().accept(this);
      c.getCond().accept(this);
      addInstruction(new Brc(bodyIndex));
    } else {
      int condIndex = program.length;
      c.getCond().accept(this);
      addInstruction(new Not());
      int endIndex = program.length;
      addInstruction(new Nop());
      c.getBody().accept(this);
      addInstruction(new Ldi(-1));
      addInstruction(new Brc(condIndex));
      program[endIndex] = new Brc(program.length);
    }
  }

  public void visit(Write c) {
    c.getExpression().accept(this);
    addInstruction(new Out());
  }

  private void addInstruction(Instruction instruction) {
    Instruction[] newProgram = new Instruction[program.length + 1];

    java.lang.System.arraycopy(program, 0, newProgram, 0, program.length);
    program = newProgram;
    program[program.length - 1] = instruction;
  }

  private void addOperatorInstruction(Object operator) throws InvalidOperator {
    if (operator == Bbinop.And) {
      addInstruction(new And());
    } else if(operator == Bbinop.Or) {
      addInstruction(new Or());
    } else if (operator == Binop.Minus) {
      addInstruction(new Sub());
    } else if (operator == Binop.Plus) {
      addInstruction(new Add());
    } else if (operator == Binop.MultiplicationOperator) {
      addInstruction(new Mul());
    } else if (operator == Binop.DivisionOperator) {
      addInstruction(new Div());
    } else if (operator == Binop.Modulo) {
      addInstruction(new Mod());
    } else if (operator == Bunop.Not) {
      addInstruction(new Not());
    } else if (operator == Comp.Equals) {
      addInstruction(new Cmp("EQUALS"));
    } else if (operator == Comp.NotEquals) {
      addInstruction(new Cmp("EQUALS"));
      addInstruction(new Not());
    } else if (operator == Comp.LessEqual) {
      addInstruction(new Pop(0));
      addInstruction(new Pop(1));
      addInstruction(new Push(1));
      addInstruction(new Push(0));
      addInstruction(new Cmp("LESS"));
      addInstruction(new Push(1));
      addInstruction(new Push(0));
      addInstruction(new Cmp("EQUALS"));
      addInstruction(new Or());
    } else if (operator == Comp.Less) {
      addInstruction(new Cmp("LESS"));
    } else if (operator == Comp.GreaterEqual) {
      addInstruction(new Cmp("LESS"));
      addInstruction(new Not());
    } else if (operator == Comp.Greater) {
      addOperatorInstruction(Comp.LessEqual);
      addInstruction(new Not());
    } else if (operator == Unop.Minus) {
      addInstruction(new Not());
      addInstruction(new Ldi(0));
      addInstruction(new Cmp("LESS"));
      addInstruction(new Brc(program.length + 5));
      addInstruction(new Ldi(1));
      addInstruction(new Sub());
      addInstruction(new Ldi(-1));
      addInstruction(new Brc(program.length + 3));
      addInstruction(new Ldi(1));
      addInstruction(new Add());
    } else {
      throw new InvalidOperator("The passed operator is invalid.");
    }
  }

  private int numOfLocalVariables() {
    return Arrays.stream(currentFunction.getDeclarations()).mapToInt(c -> c.getNames().length).sum();
  }

  private Function mainFunction(Program program) throws MissingMainFunctionException {
    for (Function function : program.getFunctions())
      if (function.getName().equals("main"))
        return function;

    throw new MissingMainFunctionException("Program must contain a function named `main`.");
  }
}
