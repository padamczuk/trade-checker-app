package lab.orion.services;

import lab.orion.authentication.Authorize;
import lab.orion.authentication.Permission;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.List;

@Path("/logout")
public class LogoutResource {

    @Inject
    @Named("loggedOut")
    private List<String> loggedOut;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response logout(@HeaderParam("authorization") String authorization) {
        String decoded = new String(Base64.getDecoder().decode(authorization.split(" ")[1]));
        loggedOut.add(decoded.split(":")[0]);
        return Response.status(200)
                .build();
    }
}
