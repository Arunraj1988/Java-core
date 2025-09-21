package library.management.util;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class LoggerUtil {
    private static final Logger LOG = Logger.getLogger("lms");
    private LoggerUtil() {}
    public static void info(String msg) {
        LOG.log(Level.INFO, "[{0}] {1}", new Object[]{LocalDateTime.now(),
                msg});
    }
    public static void error(String msg, Throwable t) {
        LOG.log(Level.SEVERE, "[{0}] {1}", new Object[]{LocalDateTime.now(),
                msg});
        if (t != null) LOG.log(Level.SEVERE, t.getMessage(), t);
    }
}
