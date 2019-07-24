package lab.orion.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.guice.ObjectMapperModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lab.orion.web.TypesModule;

public class JacksonModule extends AbstractModule {
    @Override
    protected final void configure() {
        install(new ObjectMapperModule()
                .registerModule(new JaxbAnnotationModule())
                .registerModule(new JavaTimeModule())
                .registerModule(new TypesModule()));
    }

    @Provides
    @Singleton
    public JacksonJaxbJsonProvider jacksonJaxbJsonProvider(ObjectMapper mapper) {
        return new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS);
    }
}
