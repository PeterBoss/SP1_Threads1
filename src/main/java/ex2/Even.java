package ex2;


import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>Task 2</h2>
 * <p>
 * This class should only return even numbers. It will be called from many
 * different threads, so it has to be thread-safe. In other words it has to be
 * able to avoid race-conditions if several threads calls it at once.
 * </p>
 * <p>
 * Your task is to implement the {@link #next()} method to return 0 as the
 * first number, 2 as the second, 4 and so on.
 * </p>
 */
public class Even implements Iterator<Long> {

    // The counter containing the number the iterator has reached.
    private AtomicLong counter = new AtomicLong();

    /**
     * This has 2^64 numbers. That's a lot!
     *
     * @return Always true.
     */
    @Override
    public boolean hasNext() {
        // Don't change this: we assume we always have another number
        return true;
    }

    /**
     * @return The next even number in the iterator.
     */
    @Override
    public Long next() {
        return counter.addAndGet(2);
    }

    /**
     * Test your solution here by calling {@link #next()} from different threads.
     *
     * @param args Input arguments to the main method. Unused.
     */
    public static void main(String[] args) {
        Even even = new Even();
        
        ExecutorService service = Executors.newCachedThreadPool();
        
        Runnable r = () -> System.out.println(even.next());
        
        try {
            service.execute(r);
            service.execute(r);
            service.execute(r);
            service.execute(r);
        } finally {
            service.shutdown();
        }
        
        
    }

}
