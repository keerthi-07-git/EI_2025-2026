package creationaldesignpatterns.abstractfactory.src;

public interface DBCommand {
    void execute(String query);
    void beginTransaction();
    void commit();
    void rollback();
}
