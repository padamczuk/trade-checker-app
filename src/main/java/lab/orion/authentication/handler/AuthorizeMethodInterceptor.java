package lab.orion.authentication.handler;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.inject.Provider;
import lab.orion.authentication.Authorize;
import lab.orion.authentication.Permission;
import lab.orion.authentication.exception.AuthenticationException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AuthorizeMethodInterceptor implements MethodInterceptor {
    @Inject
    private Provider<HttpServletRequest> request;
    Map<String,Long> lastUserRequest = Maps.newHashMap();
    Map<String,String> users = ImmutableMap.<String,String>builder()
            .put("user1","user1")
            .put("user2","user2")
            .build();

    @Inject
    @Named("loggedOut")
    List<String> loggedOut;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Permission[] requiredPermissions = methodInvocation.getMethod().getAnnotation(Authorize.class).requiredPermissions();
        Map<String, String> headers = Maps.newHashMap();
        Enumeration enumer = request.get().getHeaderNames();
        while (enumer.hasMoreElements()) {
            String headerName = (String)enumer.nextElement();
            headers.put(headerName, request.get().getHeader(headerName));
        }

        //try {
        Object[] arguments = methodInvocation.getArguments();
        String authorization = headers.get("Authorization")!=null?headers.get("Authorization"):headers.get("authorization")!=null?headers.get("authorization"):null;
        String encodedCredentials = authorization!=null?authorization
                .substring("Basic ".length()):"";
        String[] credentials = new String(Base64
                .getDecoder().decode(encodedCredentials)).split(":");
        if (credentials.length < 2) {
            throw new AuthenticationException();
        }
        String username = credentials[0];
        String password = credentials[1];
        if (loggedOut.contains(username)) {
            loggedOut.remove(username);
            throw new AuthenticationException();
        }
        if (users.get(username) == null || !password.equals(users.get(username))){
            throw new AuthenticationException();
        }
        if (lastUserRequest.get(username)!=null){
            Long lastRequestTimestamp = lastUserRequest.get(username);
            Long lasted = new Date().getTime() - lastRequestTimestamp;
            if (lasted > 60000) {
                lastUserRequest.put(username, null);
                throw new AuthenticationException();
            }
            lastUserRequest.put(username, new Date().getTime());
        } else {
            lastUserRequest.put(username, new Date().getTime());
        }

        return methodInvocation.proceed();
    }
}
