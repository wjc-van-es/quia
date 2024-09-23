package nl.vea;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return String.format("<h1>Hello %s,</h1>" +
                        "<h2>welcome to Quarkus REST</h2>" +
                        "<h2>The current time is %s</h2>" +
                        "<p>Running with Java %s, %s of %s at %s.</p>" +
                        "<p>OS/Arch/version: %s/%s/%s</p>",

                System.getProperty("user.name"),
                LocalDateTime.now(),
                System.getProperty("java.version"),
                System.getProperty("java.vendor.version"),
                System.getProperty("java.vendor"),
                System.getProperty("java.home"),
                System.getProperty("os.name"),
                System.getProperty("os.arch"),
                System.getProperty("os.version"));
    }
}
