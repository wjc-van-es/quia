package nl.vea;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "quia.greeting")
    String greeting;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
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

    private static String getLogoDef() {
        return new BufferedReader(
                new InputStreamReader(
                        GreetingResource.class.getResourceAsStream("/fragment-quarkus-logo.html"),
                        StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
