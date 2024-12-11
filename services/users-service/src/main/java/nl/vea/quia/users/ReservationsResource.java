package nl.vea.quia.users;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestQuery;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Objects;

@Path("/")
public class ReservationsResource {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index(
                LocalDate startDate,
                LocalDate endDate,
                String name);
    }

    @Inject
    SecurityContext securityContext;

    @RestClient
    ReservationsClient client;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index(@RestQuery LocalDate startDate, @RestQuery LocalDate endDate){
        if (Objects.isNull(startDate)){
            startDate = LocalDate.now().plusDays(1L);
        }
        if (Objects.isNull(endDate)){
            endDate = LocalDate.now().plusDays(1L);
        }
        return Templates.index(startDate, endDate, currentUserId());
    }

    private String currentUserId() {
        Principal userPrincipal = securityContext.getUserPrincipal();
        if(Objects.nonNull(userPrincipal)){
            return userPrincipal.getName();
        } else {
            return "anonymous";
        }
    }

}
