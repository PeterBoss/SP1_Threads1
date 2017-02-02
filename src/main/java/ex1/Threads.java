package ex1;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Task 1</h2>
 * <p>
 * Write three methods:
 * <ol>
 * <li>Compute and print the sum of all numbers from 1 to 1 billion.</li>
 * <li>
 * Print the numbers from 1 to 5. Pause for 2 seconds between each print.
 * </li>
 * <li>
 * Print all numbers from 10 and up to {@link Integer#MAX_VALUE}. Pause for 3
 * seconds between each print.
 * </li>
 * </ol>
 * Create three threads that run each of the above methods. Start them all
 * simultaneously from your main method. Stop / kill the thread running the
 * third method after waiting 10 seconds.
 * </p>
 */
public class Threads {

    public static void main(String[] args) throws InterruptedException {

//    Thread t1 = new Thread(() -> System.out.println((long) 1000000000 * (1000000000 + 1) / 2));  //heh!
        Thread t1 = new Thread(() -> {
            long sum = 0;
            for (int i = 1; i <= 1000000000; i++) {
                sum += i;
            }
            System.out.println(sum);
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
                if (i != 5) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        Thread t3 = new Thread(() -> {
            for (int i = 10; i <= Integer.MAX_VALUE; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10000);
        t3.stop();
    }

}
