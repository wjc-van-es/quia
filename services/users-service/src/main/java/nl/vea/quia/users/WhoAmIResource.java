package nl.vea.quia.users;

import io.quarkus.logging.Log;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/whoami")
public class WhoAmIResource {

    @Inject
    Template whoami;

    @Inject
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        var scheme = securityContext.getAuthenticationScheme();
        var userPrincipal = securityContext.getUserPrincipal();
        var info = securityContext.isSecure();
        Log.infof("securityContext %s with authenticationScheme %s and userPrincipal %s.", securityContext,
                scheme, userPrincipal);
        String userId = userPrincipal != null
                ? userPrincipal.getName() : null;
        return whoami.data("name", userId).data("userPrincipal", userPrincipal);
    }
}
