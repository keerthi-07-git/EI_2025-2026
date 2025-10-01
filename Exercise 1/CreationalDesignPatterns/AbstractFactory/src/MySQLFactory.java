package creationaldesignpatterns.abstractfactory.src;

public class MySQLFactory implements DBFactory {
    @Override
    public DBConnection createConnection() {
        return new MySQLConnection();
    }

    @Override
    public DBCommand createCommand() {
        return new MySQLCommand();
    }
}
