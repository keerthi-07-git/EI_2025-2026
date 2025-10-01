package creationaldesignpatterns.abstractfactory.src;

public interface DBFactory {
    DBConnection createConnection();
    DBCommand createCommand();
}
