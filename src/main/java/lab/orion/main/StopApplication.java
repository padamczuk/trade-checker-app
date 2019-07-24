package lab.orion.main;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class StopApplication {

    public static void main(String[] args) {
        JMXConnector connector = tryConnect();
        MBeanServerConnection connection = tryGetMBeanServerConnection(connector);
        ObjectName on = tryCreateObjectName();
        tryInvoke(connection, on);
    }

    private static MBeanServerConnection tryGetMBeanServerConnection(JMXConnector connector) {
        try {
            return connector.getMBeanServerConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object tryInvoke(MBeanServerConnection connection, ObjectName on)  {
        try {
            return connection.invoke(on, "stop", null, null);
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectName tryCreateObjectName() {
        try {
            return new ObjectName("org.eclipse.jetty.server:type=server,id=0");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JMXConnector tryConnect() {
        try {             //service:jmx:rmi://127.0.0.1:8085/jndi/rmi://127.0.0.1:8085/jmxrmi
            return JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi://127.0.0.1:8085/jndi/rmi://127.0.0.1:8085/jmxrmi"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
