package structuraldesignpatterns.adapter.src;

public class ModernLogger implements Logger {
    private final String loggerName;

    public ModernLogger(String name) {
        this.loggerName = name;
    }

    @Override
    public void logInfo(String message) {
        System.out.println("[INFO][" + loggerName + "] " + message);
    }

    @Override
    public void logError(String message) {
        System.err.println("[ERROR][" + loggerName + "] " + message);
    }

    @Override
    public void logDebug(String message) {
        System.out.println("[DEBUG][" + loggerName + "] " + message);
    }
}
