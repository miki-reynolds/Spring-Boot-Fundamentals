package org.hyperskill.applicationcontext.config;


import org.hyperskill.applicationcontext.repo.Family;
import org.hyperskill.applicationcontext.repo.Person;
import org.hyperskill.applicationcontext.repo.Workplace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//An application context is created based on a configuration class,
// which means we need a configuration class that describes what objects (beans)
// will be created inside the IoC container.
@ComponentScan()
@Configuration
public class Config {

    @Bean
    public Person personMary() {
        return new Person("Mary");
    }

    @Bean
    public Workplace workplaceMary() {
        return new Workplace("ABC Org", "123 Street");
    }

    @Bean
    public Family familyMary() {
        return new Family(4);
    }
}

/*
We filled this configuration class with one bean definition (metadata),
based on which a future bean will be created inside the container.

Here is what we "say" to our ApplicationContext:

"Create a Person object with the property name = Mary";
"Call the created bean personMary";
"Place the bean into the IoC container".

@Configuration contains the @Component annotation inside,
which also tells ApplicationContext to create a bean based on the Config class.
So, before creating the personMary bean, ApplicationContext also needs
to create a config bean and place it in the IoC container.


ABOUT MULTIPLE COMPONENTS IN THE SAME APPLICATION CONTEXT
In order for the configuration class to know about the existence of the @Component classes,
the @ComponentScan annotation is used. We put this annotation above the configuration class name:

By default, @ComponentScan scans all the classes in the current package and all of its subpackages,
looking for @Component classes (and all the other annotations containing @Component: @Service, @Configuration, and so on).
Now our context knows about the new beans: workplace and family

You can change the default behavior of @ComponentScan and explicitly specify one or more base packages for scanning:
@ComponentScan(basePackages = "packageName")
 */
