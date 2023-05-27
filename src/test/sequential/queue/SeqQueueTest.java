package test.sequential.queue;

import sequential.queue.SeqQueue;

/**
 * @author Winter Yuan
 * @version 1.0
 * @organization AtoVio
 */
public class SeqQueueTest {
    public static void main(String[] args) {
        SeqQueue<Integer> queue = new SeqQueue<>(4);
        queue.add(1);
        queue.add(2);
        System.out.println(queue);

        queue.poll();
        queue.poll();

        System.out.println("===============================");
        System.out.println(queue);
        System.out.println(queue.peek());
    }
}
