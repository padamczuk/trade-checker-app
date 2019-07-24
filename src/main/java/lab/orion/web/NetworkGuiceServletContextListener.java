package lab.orion.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import lab.orion.config.ApplicationModule;
import lab.orion.logging.GuiceDebug;

import javax.servlet.annotation.WebListener;

@WebListener
public class NetworkGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        GuiceDebug.enable();
        return Guice.createInjector(new ApplicationModule());
    }
}
