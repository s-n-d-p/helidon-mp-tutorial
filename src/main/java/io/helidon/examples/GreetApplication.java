package io.helidon.examples;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.helidon.common.CollectionsHelper;

@ApplicationScoped // application scope is shared between all web service invocations that execute
                   // within the same application
@ApplicationPath("/") // URL path that will be used to access the application
public class GreetApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return CollectionsHelper.setOf(GreetResource.class);
    }
}