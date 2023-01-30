package org.hyperskill.applicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;


@SpringBootApplication
public class ApplicationContextApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ApplicationContextApplication.class, args);
//		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
//		var context = new AnnotationConfigApplicationContext(Config.class);
//		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
//		System.out.println(context.getBean(Person.class)); // returns a Person object
//		System.out.println(context.getBean("personMary")); // returns an Object object
//		System.out.println(context.getBean("personMary", Person.class)); // returns a Person object

	}
}

/*
APPLICATION CONTEXT

Usually, in a Spring Boot application, our class is the entry point of the app,
so we annotate it with the @SpringBootApplication annotation, and in the main method, we run our application.
During the execution of the run method, an application context is created.
We can get the context and see what bean definitions it contains (like above).

@SpringBootApplication contains the @ComponentScan annotation, so our Spring Boot context
will know about our custom @Component (@Configuration, @Service, @Repository, and so on) classes.

Another way to access ApplicationContext is to use the @Autowired annotation.
Just inject the context created in the SpringApplication.run(...) method into another component.
This allows us to get the context anywhere in the application.
@Component
public class Runner implements CommandLineRunner {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        Arrays.toString(applicationContext.getBeanDefinitionNames());
    }
}
 */