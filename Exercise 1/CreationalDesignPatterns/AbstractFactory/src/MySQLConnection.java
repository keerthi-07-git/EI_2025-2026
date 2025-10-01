package creationaldesignpatterns.abstractfactory.src;

public class MySQLConnection implements DBConnection {
    @Override
    public void connect() {
        System.out.println("Connecting MySQL...");
    }
}
