package org.hyperskill.applicationcontext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        Arrays.toString(applicationContext.getBeanDefinitionNames());
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }
}