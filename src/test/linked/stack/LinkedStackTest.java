package test.linked.stack;

import linked.stack.LinkedStack;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        System.out.println(stack);
        System.out.println(stack.peek());
    }
}
