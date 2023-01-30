package org.hyperskill.beanscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")  //// or @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AtomicInteger createCounter() {
        return new AtomicInteger();
    }
}


/*
SCOPES

1. Singleton
By default, any bean in Spring has the singleton scope and this scope is exceptionally useful in typical applications.
It means that the container creates only one instance of it for the whole ApplicationContext
and injects it in all beans when expected.
Expected result for this case: 10, 10
@Scope("singleton")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)


2. Prototype
In some cases, we don't want to have the same bean used in different parts of an application.
To turn a bean into a non-singleton, there is the prototype scope.
When we use it, the container returns a new bean every time it should be injected
into the target place. To define a bean with this scope, we must use the @Scope annotation
with the string "prototype" as the value.
Expected result for this case: 7, 3
@Scope("prototype")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)

3. Other Bean Scopes
Request is a scope that allows a bean to be created and available for the whole lifecycle of an HTTP request.
It means that if a request is processed by several Spring components, the request-scoped bean
will be available in all of these components.

Session is a scope that allows a bean to be created and available for the whole HTTP session
that may include a sequence of HTTP requests connected by cookies/session ID into a single session.

Application is a scope that allows a bean to be created and available for several applications
(ApplicationContext) running in the same ServletContext. This scope is broader than
the singleton scope which is scoped to a single application context only.

Websocket is a scope that allows a bean to be created and available during the complete
lifecycle of a WebSocket session.

To use any of these scopes, you need to pass its name to the @Scope annotation,
e.g. @Scope("request"). There are also several additional annotations like
@RequestScope, @SessionScope, and @ApplicationScope, which are the shortcuts for @Scope with a corresponding value.
You can use these annotations only if your application has a dependency on the spring-web module.

*/


