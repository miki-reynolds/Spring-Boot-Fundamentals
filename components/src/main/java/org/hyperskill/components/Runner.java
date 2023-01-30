package org.hyperskill.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final PasswordGenerator generator;

    @Autowired
    public Runner(PasswordGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run(String... args) {
        System.out.println("A short password: " + generator.generate(5));
        System.out.println("A long password: " + generator.generate(10));
    }
}

/*ABOUT COMPONENTS
In Spring, a component is a special kind of class that can be autodetected
by Spring IoC and used for dependency injection. Components are mostly used to:
ensure a high level of decoupling between different parts of an application;
assign responsibilities to classes in a more efficient way.
Spring IoC automatically identifies all classes annotated with it and creates corresponding managed beans.
By default, there is only one bean for every component.
Usually, a component has one or more non-static methods that can be invoked from outside the component.
 However, in some situations, there are components without public methods.

4 ways to place @Autowired

1. Omit

2. Before the field (not recommended unless we only want to use PasswordGenerator for this component)
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private PasswordGenerator generator;

3. Before the constructor argument
@Component
public class Runner implements CommandLineRunner {
    private final PasswordGenerator generator;

    public Runner(@Autowired PasswordGenerator generator) {
        this.generator = generator;
    }
4. Before the constructor (recommennded)
@Component
public class Runner implements CommandLineRunner {
    private final PasswordGenerator generator;

    @Autowired
    public Runner(PasswordGenerator generator) {
        this.generator = generator;
    }

So, if you don't want to add PasswordGenerator to a constructor of another component,
you can just place @Autowired on the field instead.
However, it is recommended to use constructor injection over field injection.
Constructor injection makes the dependencies clearly identified, helps with thread-safety, and simplifies testing the code.

 */