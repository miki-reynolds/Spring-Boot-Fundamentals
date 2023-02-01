



public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        MyThread thread1 = new MyThread(counter);
        MyThread thread2 = new MyThread(counter);

        thread1.start(); // start the first thread
        thread1.join();  // wait for the first thread

        thread2.start(); // start the second thread
        thread2.join();  // wait for the second thread

        System.out.println(counter.getValue()); // it prints 2
    }


}