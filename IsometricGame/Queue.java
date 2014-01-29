/*
 * Queue.java
 *
 * Created on July 29, 2003, 12:19 PM
 */

package IsometricGame;

/**
 *
 * @author  jgauci
 */
// Queue algorithm from "Algorithms" (Cormen, Leiserson, Rivest) page 202:
public class Queue {
    public Queue(int num) {
        queue = new int[num];
        head = tail = 0;
        len = num;
    }
    public Queue() {
        this(400);
    }
    private int [] queue;
    int tail, head, len;
    public void enqueue(int n) {
        queue[tail] = n;
        if (tail >= (len - 1)) {
            tail = 0;
        } else {
            tail++;
        }
    }
    public int dequeue() {
        int ret = queue[head];
        if (head >= (len - 1)) {
            head = 0;
        } else {
            head++;
        }
        return ret;
    }
    public boolean isEmpty() {
        return head == (tail + 1);
    }
    public int head() {
        return queue[head];
    }
}
