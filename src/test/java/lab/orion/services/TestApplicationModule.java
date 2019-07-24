package lab.orion.services;

import com.google.inject.AbstractModule;
import lab.orion.config.ApplicationModule;

public class TestApplicationModule extends AbstractModule {

    @Override
    public void configure() {
        System.setProperty("tradechecker.config", "./src/main/resources/tradechecker.properties");
        install(new ApplicationModule());
    }
}
