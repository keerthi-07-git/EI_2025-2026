package structuraldesignpatterns.adapter.src;

public class LegacyLoggerAdapter implements Logger {
    private final LegacyLogger legacyLogger;

    public LegacyLoggerAdapter(LegacyLogger legacyLogger) {
        if (legacyLogger == null) {
            throw new IllegalArgumentException("Legacy logger cannot be null");
        }
        this.legacyLogger = legacyLogger;
    }

    @Override
    public void logInfo(String message) {
        legacyLogger.writeLog("info", message);
    }

    @Override
    public void logError(String message) {
        legacyLogger.writeLog("error", message);
    }

    @Override
    public void logDebug(String message) {
        legacyLogger.writeLog("debug", message);
    }
}
