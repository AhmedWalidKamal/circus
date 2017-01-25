package logs;

import org.apache.logging.log4j.LogManager;


public class LogsManager {

    private static LogsManager instance;

    private LogsManager() {

    }

    public static LogsManager getInstance() {
        if (instance == null) {
            synchronized(LogsManager.class) {
                if (instance == null) {
                    instance = new LogsManager();
                }
            }
        }
        return instance;
    }

   // private static final Logger logger = LogManager.getLogger(LogsManager.class.getName());

    public void info (final String message) {
        LogManager.getLogger(LogsManager.class.getName()).info(message);
    }

    public void error (final String message) {
        LogManager.getLogger(LogsManager.class.getName()).error(message);
    }
}
