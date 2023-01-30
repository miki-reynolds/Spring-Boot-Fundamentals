package org.hyperskill.beansvscomponents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hyperskill.beansvscomponents.PasswordConfig.*;
import java.util.Random;

@Component
public class PasswordGenerator {
    private static final Random random = new Random();
    private final PasswordAlphabet alphabet;

    @Autowired
    public PasswordGenerator(PasswordAlphabet alphabet) {
        this.alphabet = alphabet;
    }

    public String generate(int length) {
        String allCharacters = alphabet.getCharacters(); // get the characters from the bean
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allCharacters.length());
            result.append(allCharacters.charAt(index));
        }
        return result.toString();
    }
}

/*
BEANS VS COMPONENTS
The @Bean annotation is a method-level annotation and @Component is a class level annotation.
The @Component annotation doesn't need to be used with the @Configuration annotation
 whereas the @Bean annotation has to be used within the class which is annotated with @Configuration.
If you want to create a bean for a class from an external library, you cannot just add the @Component annotation
 because you cannot edit the class. However, you can declare a method annotated with @Bean
 and return an object of this class from this method.
There are several specializations of the @Component annotation, whereas @Bean doesn't have them.
In most cases, you can use both approaches but Spring developers typically prefer @Component whenever possible.
The @Bean annotation is mostly used for producing beans of unmodifiable classes or creating some configs.

Specializations of components
As we mentioned before, there are several specializations of components depending on their role in Spring applications:

@Component indicates a generic Spring-managed component;
@Service indicates a business logic component but doesn't provide any additional functions;
@Controller / @RestController indicates a component that can work in a web environment;
@Repository indicates a component that interacts with an external data storage (e.g. a database).
 */