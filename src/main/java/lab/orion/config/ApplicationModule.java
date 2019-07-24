package lab.orion.config;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    protected final void configure() {
        install(new JerseyModule());
        install(new JacksonModule());
        install(new PropertiesModule());
        install(new TradeCheckerModule());
    }
}
