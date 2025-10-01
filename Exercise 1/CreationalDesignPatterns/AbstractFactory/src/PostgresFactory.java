package creationaldesignpatterns.abstractfactory.src;

public class PostgresFactory implements DBFactory {
    @Override
    public DBConnection create() {
        return new PostgresConnection();
    }
}
