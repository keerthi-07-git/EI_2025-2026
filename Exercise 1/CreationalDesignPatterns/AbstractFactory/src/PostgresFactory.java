package creationaldesignpatterns.abstractfactory.src;

public class PostgresFactory implements DBFactory {
    @Override
    public DBConnection createConnection() {
        return new PostgresConnection();
    }

    @Override
    public DBCommand createCommand() {
        return new PostgresCommand();
    }
}
