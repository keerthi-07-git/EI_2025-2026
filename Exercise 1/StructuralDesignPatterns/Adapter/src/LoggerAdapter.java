package structuraldesignpatterns.adapter.src;

public class LoggerAdapter implements ModernLogger {
    private final LegacyLogger legacy;

    public LoggerAdapter(LegacyLogger l) {
        legacy = l;
    }

    @Override
    public void info(String msg) {
        legacy.logMsg(msg);
    }
}
