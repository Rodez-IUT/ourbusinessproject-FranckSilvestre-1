package ourbusinessproject;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {
    private final InitializationService initializationService;
    // a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public Bootstrap(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    /**
     * This method is called when the application is started.
     * It initializes the project data.
     */
    @PostConstruct
    public void init() {
        try {
            initializationService.initProjects();
        } catch (RuntimeException e) {
            logger.error("Error during initialization", e);
        }
    }

    /**
     * @return the initialization service
     */
    public InitializationService getInitializationService() {
        return initializationService;
    }
}
