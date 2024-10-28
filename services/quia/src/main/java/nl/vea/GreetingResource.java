package nl.vea;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class.getName());

    @ConfigProperty(name = "quia.greeting")
    String greeting;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        LOGGER.debug(greeting);
        return String.format("<p>%s</p>" +
                "<h1>Hello %s,</h1>" +
                        "<h2>%s</h2>" +
                        "<h2>The current time is %s</h2>" +
                        "<p>Running with Java %s, %s of %s at %s.</p>" +
                        "<p>OS/Arch/version: %s/%s/%s</p>",
                getLogoDef(),
                System.getProperty("user.name"),
                greeting,
                LocalDateTime.now(),
                System.getProperty("java.version"),
                System.getProperty("java.vendor.version"),
                System.getProperty("java.vendor"),
                System.getProperty("java.home"),
                System.getProperty("os.name"),
                System.getProperty("os.arch"),
                System.getProperty("os.version"));
    }

    @GET
    @Path("/whoami")
    @Produces(MediaType.TEXT_PLAIN)
    public String whoAmI(@Context SecurityContext securityContext) {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if (userPrincipal != null) {
            return userPrincipal.getName();
        } else {
            return "anonymous";
        }
    }

    private static String getLogoDef() {
        return new BufferedReader(
                new InputStreamReader(
                        GreetingResource.class.getResourceAsStream("/fragment-quarkus-logo.html"),
                        StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
