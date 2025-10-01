package creationaldesignpatterns.abstractfactory.src;

public class MySQLFactory implements DBFactory {
    @Override
    public DBConnection create() {
        return new MySQLConnection();
    }
}
