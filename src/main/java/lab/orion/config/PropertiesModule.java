package lab.orion.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    @Override
    protected final void configure() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(System.getProperty("tradechecker.config")));
            Names.bindProperties(binder(), properties);
        } catch (FileNotFoundException e) {
            System.out.println("The configuration file tradechecker.config can not be found");
        } catch (IOException e) {
            System.out.println("I/O Exception during loading configuration");
        }
    }
}
