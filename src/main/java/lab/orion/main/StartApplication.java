package lab.orion.main;

import lab.orion.web.NetworkGuiceFilter;
import lab.orion.web.NetworkGuiceServletContextListener;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.management.MBeanServer;
import javax.servlet.DispatcherType;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;

public class StartApplication {
    public static void main(String[] args) {
        Server server = start();
        try {
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Server start() {
        Server server = new Server();
        ServerConnector connector = createServerConnector(server);
        server.addConnector(connector);
        ServletContextHandler context = createServletContextHandler();
        server.setHandler(context);
        MBeanContainer mBeanContainer = createMBeanContainer();
        server.addEventListener(mBeanContainer);
        server.addBean(mBeanContainer);
        try {
            server.start();
            return server;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static MBeanContainer createMBeanContainer() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        return new MBeanContainer(mBeanServer);
    }

    private static ServletContextHandler createServletContextHandler() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.addServlet(DefaultServlet.class, "/*");
        context.setContextPath("/trade-checker-app");
        context.addEventListener(new NetworkGuiceServletContextListener());
        context.addFilter(NetworkGuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));//Handler.DEFAULT
        return context;
    }

    private static ServerConnector createServerConnector(Server server) {
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("127.0.0.1");
        connector.setPort(8080);
        connector.setIdleTimeout(3000);
        return connector;
    }
}
