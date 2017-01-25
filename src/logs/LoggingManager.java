package logs;

import org.apache.logging.log4j.LogManager;

public class LoggingManager {

    private static LoggingManager instance;

    private LoggingManager() {

    }

    public static LoggingManager getInstance() {
        if (instance == null) {
            synchronized(LoggingManager.class) {
                if (instance == null) {
                    instance = new LoggingManager();
                }
            }
        }
        return instance;
    }

    // private static final Logger logger = LogManager.getLogger(LoggingManager.class.getName());

    public void info (String message) {
        LogManager.getLogger(LoggingManager.class.getName()).info(message);
    }

    public void error (String message) {
        LogManager.getLogger(LoggingManager.class.getName()).error(message);
    }
}
