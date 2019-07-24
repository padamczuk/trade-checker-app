package lab.orion.authentication.handler;

import lab.orion.authentication.dto.ErrorMessage;
import lab.orion.authentication.exception.AuthenticationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {
    public Response toResponse(AuthenticationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("brak autentykacji");
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(errorMessage)
                .header("WWW-Authenticate","Basic realm=\"User Visible Realm\"")
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
