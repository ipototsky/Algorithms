package week2.quiz;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import week2.Stack;


public class DoubleStackQueue<Item> {

  private Stack<Item> first = new Stack<Item>();
  
  private Stack<Item> second = new Stack<Item>();
  
  public void enqueue(Item item) {
    first.push(item);
  }
  
  public Item dequeue() {
    if (second.isEmpty()) {
      while (!first.isEmpty()) {
        second.push(first.pop());
      }
    }
    return second.pop();
  }
  
  public static void main(String[] args) {
    DoubleStackQueue<String> queue = new DoubleStackQueue<String>();
    while (!StdIn.isEmpty()) {
      String string = StdIn.readString();

      if (string.equals("-")) {
        StdOut.print(queue.dequeue());
      } else {
        queue.enqueue(string);
      }
    }
  }
}
