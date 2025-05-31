package app;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static ApplicationContext appContext;

    public static void main(String[] args) {
        LOG.info("Starting server");
        appContext = Micronaut.run(Main.class, args);
        LOG.info("Server started");
    }
}