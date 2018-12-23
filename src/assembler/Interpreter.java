package assembler;

import java.util.Scanner;

public class Interpreter implements AsmVisitor {
  private int[] stack = new int[0];
  private int r0;
  private int r1;
  private int sp = -1;
  private int fp = -1;
  private int pc;
  private boolean halt;
  private Instruction[] instructions;

  public Interpreter(Instruction[] instructions) {
    this.instructions = instructions;
  }

  public int execute() throws InstructionOverflowException, StackManagementException {
    while (!halt) {
      pc++;

      if (pc > instructions.length) throw new InstructionOverflowException("Missing HALT instruction.");
      if (pc < 0) throw new InstructionOverflowException("Undefined program counter value.");
      instructions[pc - 1].accept(this);
    }

    if (stack.length < 1) throw new StackManagementException("At the end of a program the stack should contain at least one element.");
    return stack[0];
  }

  public void visit(Add c) {
    push(pop() + pop());
  }

  public void visit(And c) {
    push(pop() & pop());
  }

  public void visit(Brc c) {
    if (pop() == -1)
      pc = c.getI();
  }

  public void visit(Call c) {
    int[] arguments = new int[c.getN()];
    int functionIndex = pop();

    for (int i = arguments.length - 1; i >= 0; i--)
      arguments[i] = pop();
    push(pc);
    push(fp);
    for (int argument : arguments) push(argument);
    fp = sp;

    pc = functionIndex;
  }

  public void visit(Cmp c) {
    if (c.getOp().equals("EQUALS"))
      push(pop() == pop() ? -1 : 0);
    else if (c.getOp().equals("LESS"))
      push(pop() < pop() ? -1 : 0);
  }

  public void visit(Decl c) {
    int[] newStack = new int[stack.length + c.getN()];

    java.lang.System.arraycopy(stack, 0, newStack, 0, stack.length);
    sp = newStack.length - 1;
    stack = newStack;
  }

  public void visit(Div c) {
    push(pop() / pop());
  }

  public void visit(Halt c) {
    halt = true;
  }

  public void visit(In c) {
    Scanner reader = new Scanner(System.in);
    System.out.println("Input: ");
    push(reader.nextInt());
  }

  public void visit(Ldi c) {
    push(c.getC());
  }

  public void visit(Lfs c) throws StackManagementException {
    int stackIndex = fp + c.getI();
    if (stack.length - 1 < stackIndex) throw new StackManagementException("This address on the stack is invalid.");

    push(stack[stackIndex]);
  }

  public void visit(Mod c) {
    push(pop() % pop());
  }

  public void visit(Mul c) {
    push(pop() * pop());
  }

  public void visit(Nop c) {}

  public void visit(Not c) {
    push(~pop());
  }

  public void visit(Or c) {
    push(pop() | pop());
  }

  public void visit(Out c) {
    System.out.println(pop());
  }

  public void visit(Pop c) {
    if (c.getI() == 0)
      r0 = pop();
    else if (c.getI() == 1)
      r1 = pop();
  }

  public void visit(Push c) {
    if (c.getI() == 0)
      push(r0);
    else if (c.getI() == 1)
      push(r1);
  }

  public void visit(Return c) throws StackManagementException {
    int result = pop();
    if (c.getN() > stack.length) throw new StackManagementException("Cannot return with more addresses than there are in the stack.");
    int[] newStack = new int[stack.length - c.getN()];

    java.lang.System.arraycopy(stack, 0, newStack, 0, newStack.length);
    sp = newStack.length - 1;
    stack = newStack;

    fp = pop();
    pc = pop();

    push(result);
  }

  public void visit(Sts c) throws StackManagementException {
    int var = pop(); // Does not work inline!
    int stackIndex = fp + c.getI();
    if (stack.length - 1 < stackIndex) throw new StackManagementException("This address on the stack is invalid.");

    stack[stackIndex] = var;
  }

  public void visit(Sub c) {
    push(pop() - pop());
  }

  private void push(int value) {
    int[] newStack = new int[stack.length + 1];

    java.lang.System.arraycopy(stack, 0, newStack, 0, stack.length);
    newStack[++sp] = value;
    stack = newStack;
  }

  private int pop() throws StackManagementException {
    if (stack.length == 0) throw new StackManagementException("Cannot pop element from an empty stack.");
    int[] newStack = new int[stack.length - 1];
    int result = stack[sp--];

    java.lang.System.arraycopy(stack, 0, newStack, 0, newStack.length);
    stack = newStack;

    return result;
  }
}
