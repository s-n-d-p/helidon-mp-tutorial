package io.helidon.examples;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/greet") // set the path for this resource
@RequestScoped // define this bean as RequestScoped => request scope is active only for the
               // duration of one web service invocation and it is destroyed at the end of that
               // invocation
public class GreetResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private final GreetingProvider greetingProvider;

    @Inject
    public GreetResource(GreetingProvider greetingProvider) {
        this.greetingProvider = greetingProvider;
    }

    @GET // accepts HTTP GET method
    @Produces(MediaType.APPLICATION_JSON) // this method will return JSON data
    public JsonObject getDefaultMessage() {
        return createResponse("World");
    }

    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getMessage(@PathParam("name") String name) {
        return createResponse(name);
    }

    @Path("/greeting")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGreeting(JsonObject jsonObject) {
        if (!jsonObject.containsKey("greeting")) {
            JsonObject entity = JSON.createObjectBuilder().add("error", "No greeting provided").build();
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }

        String newGreeting = jsonObject.getString("greeting");
        greetingProvider.setMessage(newGreeting);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private JsonObject createResponse(String who) {
        String msg = String.format("%s %s!", greetingProvider.getMessage(), who);

        return JSON.createObjectBuilder().add("message", msg).build();
    }
}