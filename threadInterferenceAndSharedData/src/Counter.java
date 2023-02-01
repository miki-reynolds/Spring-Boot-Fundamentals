class Counter {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public synchronized int getValue() {
        return value;
    }
}

/*
The initial value of the field is 0.

Now if Thread A invokes the method increment of this instance and Thread B also invokes the method at the same time,
the following happens in the case without SYNCHRONIZED KEYWORD:

Thread A: read value from the variable.
Thread A: increment the read value by 1.
Thread B: read value from the variable (it reads an intermediate value 0).
Thread A: write the result in the variable (now, the current value of the field is 1).
Thread B: increment the read value by 1.
Thread B: write the result in the variable (now, the current value of the field is 1).
In this case after calling the method increment from two threads we may obtain the unexpected result (1 instead of 2).
That means that the result of Thread A was lost, overwritten by Thread B.
Although sometimes the result may be correct, this particular interleaving is possible.

We've just seen how increment and decrement are non-atomic operations.
In this topic, we will not discuss how this problem may be solved, just keep it in mind for now.

Let's consider another case: an assignment of 64-bit values. It may be surprising,
but even the reading and writing fields of double and long types (64-bits) may not be atomic on some platforms.
It means while a thread writes a value to a variable, another thread can access an intermediate result
(for example, only 32 written bits). To make these operations atomic, fields should be declared using the volatile keyword.

The reading of and writing to the fields of other primitive types
(boolean, byte, short, int, char, float) are guaranteed to be atomic.
 the volatile keyword allows us to make visible changes of a field made by one thread to other threads.
 This keyword also makes writing to double and long fields atomic. But the keyword doesn't make the increment/decrement and similar operations atomic.

 class MyClass {

    volatile long longVal; // reading and writing are atomic now

    volatile double doubleVal; // reading and writing are atomic now
}

 */