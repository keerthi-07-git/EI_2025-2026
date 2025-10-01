package structuraldesignpatterns.adapter.src;

public class LegacyLogger {
    public void logMsg(String msg) {
        System.out.println("Legacy: " + msg);
    }
}
