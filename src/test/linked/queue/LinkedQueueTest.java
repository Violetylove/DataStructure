package test.linked.queue;

import linked.queue.LinkedQueue;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class LinkedQueueTest {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue);

        queue.poll();
        queue.poll();

        System.out.println("===========================");
        System.out.println(queue.peek());
    }
}
