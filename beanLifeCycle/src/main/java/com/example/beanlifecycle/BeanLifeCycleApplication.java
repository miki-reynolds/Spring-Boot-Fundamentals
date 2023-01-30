package com.example.beanlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanLifeCycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanLifeCycleApplication.class, args);
    }

}

/*
As seen in pic, the presented lifecycle is the same for both @Bean-annotated methods and @Component-annotated classes.
However, there is a significant difference between singletons and beans annotated with Scope("prototype").
Spring doesn't destroy prototypes and doesn't allow us to customize them.

Customizing bean initialization and destruction
To get a bean into the ready state, some initialization after dependency injection might be required.
The Spring container does it automatically, but it also allows us to customize the initialization
according to the needs of our application. For example, we can load some resources, read a file, connect to a database,
and so on. At the same time, when a bean is no longer required in the application, some custom cleanup may be required
before destroying the bean, such as closing some connections, cleaning files, and so on.

There are several ways to add these customizations to your code:

using special annotations (@PostConstruct, @PreDestroy, @Bean);
implementing some interfaces (InitializingBean, DisposableBean);
using an XML bean definition file, which is an outdated way mostly used for legacy applications (we will skip it here).
No matter which way you choose, you need to have separate methods that conduct some custom bean initialization and destruction. These are the rules for such methods:

the methods can have any names and access modifiers;
the methods must not have any arguments, otherwise, an exception will be thrown.

1. Using annotations for customization
- @Component
The simplest way to customize the initialization and destruction processes is to add the @PostConstruct and @PreDestroy
annotations to the methods of a container-managed class.

Here is an example with the init and destroy methods annotated with @PostConstruct and @PreDestroy.
If we run an application containing this component, Spring will call the annotated methods only once.

 - @Bean
Same result with @Bean. What you need to do is specify the names of the init and destroy methods
as the values of initMethod and destroyMethod properties of the @Bean annotation.

The following code is equivalent to the one above, but this new version uses @Bean instead of @Component.

2. Using interfaces for customization
Another way to customize the initialization and destruction processes is to implement the InitializingBean and
DisposableBean interfaces, and then override their afterPropertiesSet and destroy methods correspondingly.

3. Post-processors for beans
Another way that is even more straightforward. You can initialize beans using the BeanPostProcessor interface
that allows for custom modification of bean instances. Using the post-processor, it is possible to run any
custom operation before or right after a bean is initialized and even return a modified bean.

To start using post-processors, your class should:

Implement the BeanPostProcessor interface;
Override the postProcessBeforeInitialization and/or postProcessAfterInitialization methods.
 */
