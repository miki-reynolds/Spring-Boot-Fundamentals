import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> synchronizedNumbers = Collections.synchronizedMap(new HashMap<>());

        Thread writer = new Thread(() -> addPositiveNumbers(synchronizedNumbers));
        writer.start();

        addNegativeNumbers(synchronizedNumbers); // add negative numbers from the main thread

        writer.join(); // wait for the writer thread

        System.out.println(synchronizedNumbers.size()); // the size is always 200_000

        synchronized (synchronizedNumbers) {
            Iterator<Integer> iterator = synchronizedNumbers.keySet().iterator();
            while (iterator.hasNext()) {
                // do important things
                iterator.next();
            }
        }

//      ConcurrentHashMap Method
        Map<Integer, String> map = new ConcurrentHashMap<>();

        Thread writer1 = new Thread(() -> addPositiveNumbers(map));
        Thread writer2 = new Thread(() -> addNegativeNumbers(map));

        writer1.start();
        writer2.start();

        // Here, in the main thread, we can use Iterator, retrieve values or update the map

        writer1.join(); // wait for writer1 thread
        writer2.join(); // wait for writer2 thread

        System.out.println(map.size()); // the result is always the same
    }

    private static void addPositiveNumbers(Map<Integer, String> target) {
        for (int i = 0; i < 100_000; i++) {
            target.put(i, "Number is " + i);
        }
    }

    private static void addNegativeNumbers(Map<Integer, String> target) {
        for (int i = -100_000; i < 0; i++) {
            target.put(i, "Number is " + i);
        }
    }



}

/*
So if your map is meant to be used by several threads, there are two paths for you:
ConcurrentHashMap and Collections.synchronizedMap. Let's start with the firstborn!

1. Collections.synchronizedMap
Everything works as intended and the resulting size of the map is always the same.
However, if you experiment and use a classic non-synchronized map instead of a synchronizedMap ,
the size of the returned map can be totally unpredictable.

Here are the main features of the Collections.synchronizedMap to keep in mind:
The synchronization is performed by an object.
The methods (and as a result, all operations) of a synchronizedMap are protected by a lock, which provides thread-safe access.
To iterate over a synchronizedMap you need to use a synchronized block:
Without the synchronized block the iterator may throw ConcurrentModificationException. This will happen if one thread is iterating over a map and another thread is trying to modify our map.
Only one thread at a time has access to the map, other threads are blocked.


2. ConcurrentHashMap
 The secret is in Lock Striping. This technique means that the lock mechanism occurs only on separate stripes
 (or buckets), but not on the whole data structure.
When a thread accesses the stripe, it locks only that stripe, leaving other stripes available.

Here are the main features of ConcurrentHashMap:
The synchronization is performed at the bucket level.
Retrieval operations don't require a block.
The iterator won't throw ConcurrentModificationException when one thread is iterating over a map and another thread is trying to modify it. However, there is no guarantee, that iterator will see changes made by another thread.
The number of threads working simultaneously with the ConcurrentHashMap can increase if the size of the ConcurrentHashMap has been increased.
Note that ConcurrentHashMap doesn't allow using null as a key or a value


Similar problems occur when multiple threads access a collection:

most of the classic collections like ArrayList, LinkedList, HashMap and others are non-synchronized and,
as a consequence, they do not provide thread-safety;
there is a set of old collections like Vector, Stack, and Hashtable that are totally synchronized
and thread-safe, but they have low performance; when one thread is iterating
over a standard totally synchronized collection and another thread tries to add a new element to it
then we get a runtime exception called ConcurrentModificationException.

To avoid all the problems associated with custom synchronization, Java Class Library provides
alternative collection implementations that are adapted to be used in multithreaded applications
and they are fully thread-safe. You may find them in the java.util.concurrent package that includes
lists, queues, maps and other collections which make it easier to develop modern Java applications.
 */