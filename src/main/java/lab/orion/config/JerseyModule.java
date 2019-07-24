package lab.orion.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import lab.orion.authentication.Authorize;
import lab.orion.authentication.handler.AuthenticationExceptionMapper;
import lab.orion.authentication.handler.AuthorizeMethodInterceptor;
import org.glassfish.jersey.client.ClientConfig;

import java.util.ArrayList;
import java.util.List;

//import com.hp.cache4guice.adapters.ehcache.EhCacheModule;

public class JerseyModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
//        install(new EhCacheModule());
        configureAuthorization();
        bind(GuiceContainer.class).asEagerSingleton();
        serve("/rest/*").with(GuiceContainer.class);
        bind(AuthenticationExceptionMapper.class);
    }

    @Provides
    @Singleton
    public ClientConfig clientConfig(JacksonJaxbJsonProvider jacksonJaxbJsonProvider) {
        return new ClientConfig().register(jacksonJaxbJsonProvider);
    }
    private void configureAuthorization() {
        bind(new TypeLiteral<List<String>>() {})
                .annotatedWith(Names.named("loggedOut"))
                .to(new TypeLiteral<ArrayList<String>>() {})
                .asEagerSingleton();
        AuthorizeMethodInterceptor myInterceptor = new AuthorizeMethodInterceptor();
        requestInjection(myInterceptor);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Authorize.class), myInterceptor);
    }
}
