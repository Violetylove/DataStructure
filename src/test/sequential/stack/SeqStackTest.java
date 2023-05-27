package test.sequential.stack;

import sequential.stack.SeqStack;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SeqStackTest {
    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        stack.pop();

        System.out.println();
        System.out.println(stack.isEmpty());
    }
}
