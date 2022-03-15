package utils;

public class LoggerUtils {

    public static void doOnLogRequest (Runnable runnable) {
        boolean logRequest = System.getProperty("logRequest") != null;
        if (logRequest) {
            runnable.run();
        }
    }

    public static void doOnLogResponse (Runnable runnable) {
        boolean logResponse = System.getProperty("logResponse") !=null;
        if (logResponse) {
            runnable.run();
        }
    }

}
