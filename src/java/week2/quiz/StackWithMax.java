package week2.quiz;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import week2.Stack;


public class StackWithMax {
  private Stack stack = new Stack();
  private Stack max = new Stack();
  
  public void push(int value) {
    stack.push(value);
    int maximum;
    if (max.isEmpty()) {
      maximum = value;
    } else {
      int curMax = (int) max.peek();
      maximum = curMax > value ? curMax : value; 
    }
    max.push(maximum);
  }
  
  public int pop() {
    max.pop();
    return (int) stack.pop();
  }
  
  public int getMax() {
    if (max.isEmpty()) {
      System.out.println("Stack is empty");
      return -1;
    } else {
      return (int) max.peek();
    }
  }
  
  public static void main(String[] args) {
    StackWithMax stack = new StackWithMax();
    while (!StdIn.isEmpty()) {
      String string = StdIn.readString();

      if (string.equals("-")) {
        StdOut.print(stack.pop());
      } else if (string.equals("max")) {
        StdOut.print(stack.getMax());
      } else {
        stack.push(Integer.valueOf(string));
      } 
    }
  }
}
