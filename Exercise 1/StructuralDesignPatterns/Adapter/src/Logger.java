package structuraldesignpatterns.adapter.src;

public interface Logger {
    void logInfo(String message);
    void logError(String message);
    void logDebug(String message);
}
