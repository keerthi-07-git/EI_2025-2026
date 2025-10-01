package structuraldesignpatterns.adapter.src;

public class StructuralAdapter {
    public static void main(String[] args) {
        ModernLogger log = new LoggerAdapter(new LegacyLogger());
        log.info("System started");
    }
}
