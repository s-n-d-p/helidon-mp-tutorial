package io.helidon.examples;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness // tells Helidon that this class provides a custom health check
@ApplicationScoped
public class GreetHealthCheck implements HealthCheck {
    private GreetingProvider provider;

    @Inject
    public GreetHealthCheck(GreetingProvider provider) {
        this.provider = provider;
    }

    @Override
    public HealthCheckResponse call() {
        String message = provider.getMessage();
        return HealthCheckResponse.named("greeting").state("Hello".equals(message)).withData("greeting", message)
                .build();
    }

}