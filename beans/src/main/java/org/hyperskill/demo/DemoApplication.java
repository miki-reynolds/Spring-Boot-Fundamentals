package org.hyperskill.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public String address() {
        return "Green Street, 101";
    }

    @Bean
    public String address1() {
        return "Green Street, 102";
    }

    @Bean
    public String address2() {
        return "Apple Street, 15";
    }

    // use Qualifier annotation to identify which bean we want to use when there are many of the same type.
    @Bean
    public Customer customer(@Qualifier("address2") String address) {
        return new Customer("Clara Foster", address);
    }

    @Bean
    public Customer temporary(@Autowired Customer customer) {
        // Customer{name='Clara Foster', address='Apple Street, 15'}
        System.out.println(customer);
        return customer;
    }

}
/*
ABOUT IoC (INVERSION of CONTROL)

https://vn.got-it.ai/blog/inversion-of-control-la-gi
IoC, which stands for Inversion of Control, is the mechanism used by Spring to implement dependency injection.
 When we create applications, we often need different objects to implement various functionalities.
 Some objects will need to use other objects as their dependencies, which in turn may require other objects, and so on.
 In order to simplify this long and complex process, Spring uses dependency injection.

The Spring container can be configured through metadata in a number of ways.
 There are two types of metadata that are used in Spring: XML and annotations.
 1. The XML approach involves defining class-related data in an external XML file,
 which can then be loaded and used in the Spring application.
 2. The annotation-based approach involves adding annotations to simple classes in order to provide context
 and functionality for Spring. These annotations will start with the @ character, and provide a specific value that we wish to add to our class. These values will allow us to build objects with the required features and configurations.
 These objects are known as POJO classes, and in the next section, we will see how they fit into the Spring framework.
The simplicity of POJO (Plain Old Java Object) makes them ideal building blocks for any application component
 we need to implement. Beside POJOs, Spring can use a special type of POJO called JavaBean.
 With JavaBeans, we add a few more requirements: for example, classes are required to be serializable.
 In addition, they require private fields and a no-argument constructor to be available.
 These classes can also be customized and configured using Spring metadata.
 To do this, we can add various annotations to the classes we create in Spring.

 COMPONENTS
When we work with the Spring IoC, there are two components we should be aware of.
1. the BeanFactory, an interface that allows for configuration and management of objects.
The BeanFactory can be used to produce container-managed objects known as beans,
which can organize the backbone of your application. These beans look like regular Java objects,
but they can be created during application startup, registered, and injected into different parts of
the application by the container.

2. The ApplicationContext, which is a sub-interface of the BeanFactory.
The goal of the ApplicationContext is to facilitate integration with Spring's Aspect Oriented Programming (AOP)
functionality. This functionality includes a variety of components, ranging from message resource handling
to application layer-specific contexts. There are three main ApplicationContext implementations that we typically
see in applications:
FileSystemXmlApplicationContext
ClassPathXmlApplicationContext
WebApplicationContext

The FileSystemXmlApplicationContext will load bean definitions from an XML file that is
provided as a full file system path to the constructor. This means that beans are initialized based on the contents
of a file from the file system of the application that is being run.

For ClassPathXmlApplicationContext, beans are still loaded from an XML file, however, the file is provided as
the CLASSPATH property rather than a full system path in the constructor.

Finally, WebApplicationContext is generally used to set the configuration of a web application in Spring.
When using WebApplicationContext, you will often set the servlet configuration within a web.xml file.
Inside this file, you can specify configurations for each servlet that the application uses.

With our ApplicationContext, we can configure the Spring IoC container,
allowing us to create an application that is ready for use.

ABOUT BEANS
We often need to create different objects in an application to use their functionalities.
Some of them need other objects as their dependencies, which in turn require other objects and so on.
Spring offers a great way to simplify this huge and complicated chain of creating objects.
It can create all the necessary objects during the application startup and put them all in a container.
Then each class can retrieve any objects it needs from this container,
no more creation and initialization constructions are needed!

These container-managed objects are known as beans and they organize the backbone of your application.
They look exactly like standard Java or Kotlin objects but can be created during the application startup, registered,
and then injected into different parts of an application by the container.
 */