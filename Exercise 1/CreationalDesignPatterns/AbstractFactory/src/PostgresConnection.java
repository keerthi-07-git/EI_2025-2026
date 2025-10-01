package creationaldesignpatterns.abstractfactory.src;

public class PostgresConnection implements DBConnection {
    @Override
    public void connect() {
        System.out.println("Connecting PostgreSQL...");
    }
}
