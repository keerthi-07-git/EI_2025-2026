package creationaldesignpatterns.abstractfactory.src;

public class CreationalAbstractFactory {
    public static void main(String[] args) {
        DBFactory factory = new PostgresFactory();
        DBConnection conn = factory.create();
        conn.connect();
    }
}
