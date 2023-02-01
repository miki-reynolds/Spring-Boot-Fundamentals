package org.hyperskill.threads;


import java.lang.Thread;


public class ThreadsApplication {
    public static void main(String[] args) {
        /**
         * Generic Thread methods
         */
//        Thread t = new Thread(() -> do something);
        Thread t = Thread.currentThread(); // main thread

        System.out.println("Name: " + t.getName());
        System.out.println("ID: " + t.getId());
        System.out.println("Alive: " + t.isAlive());
        System.out.println("Priority: " + t.getPriority());
        System.out.println("Daemon: " + t.isDaemon());

        t.setName("my-thread");
        System.out.println("New name: " + t.getName());
    }

    public static void CustomThread() {
        /**
         * 2 ways: extends Thread or implements Runnable interface
         */
        class HelloThread extends Thread {

            @Override
            public void run() {
                String helloMsg = String.format("Hello, i'm %s", getName());
                System.out.println(helloMsg);
            }
        }

        class HelloRunnable implements Runnable {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String helloMsg = String.format("Hello, i'm %s", threadName);
                System.out.println(helloMsg);
            }
        }
//      Init custom threads
//      Thread t1 = new HelloThread(); // a subclass of Thread
//      Thread t2 = new Thread(new HelloRunnable()); // passing runnable
//      Thread myThread = new Thread(new HelloRunnable(), "my-thread"); specify the name
//      thread.start() starts a new thread and runs it
//      thread.start() runs this in the same thread


    }

    public static void JoiningMethod throws InterruptedException {
        class Worker extends Thread {
            @Override
            public void run() {
                try {
                    System.out.println("Starting a task");
                    Thread.sleep(2000L); // it solves a difficult task
                    System.out.println("The task is finished");
                } catch (Exception ignored) {
                }
            }
        }

        Thread worker = new Worker();
        worker.start(); // start the worker
        Thread.sleep(100L);
        System.out.println("Do something useful");
        worker.join(3000L);  // waiting for the worker
        System.out.println("The program stopped");
        }
    }

//    4 outputs
//Starting a task (1) (2)
//Do something useful (2) (1)
//The task is finished (3) (4)
//The program stopped (4) (3)
}


/*
SOME NOTES
The invocation t.isDaemon() checks whether the thread is a daemon.
A daemon thread (which comes from UNIX terminology) is a low-priority thread that runs in the background
to perform tasks such as garbage collection and so on. JVM does not wait for daemon threads before exiting
whereas it waits for non-daemon threads.
 */