package structuraldesignpatterns.adapter.src;

public class LegacyLogger {
    public void writeLog(String level, String msg) {
        System.out.println("[LEGACY-" + level.toUpperCase() + "] " + msg);
    }
}
