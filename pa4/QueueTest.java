//Steven Cruz
//CS12B
//PA3
//sicruz

//-----------------------------------------------------------------------------
// QueueTest.java
// Test Client for the Queue class
//-----------------------------------------------------------------------------

public class QueueTest {
    public static void main(String[] args) {
        Queue A = new Queue();

        A.enqueue(5); A.enqueue(3); A.enqueue(9); A.enqueue(7); A.enqueue(10);
        System.out.println(A);
        A.dequeue(); A.dequeue();
        System.out.println(A);
        System.out.println(A.length());
        System.out.println(A.peek());
        A.dequeue(); A.dequeue();
        System.out.println(A.peek());
    }
}