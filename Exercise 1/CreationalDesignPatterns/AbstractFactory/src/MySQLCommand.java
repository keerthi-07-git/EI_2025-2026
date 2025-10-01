package creationaldesignpatterns.abstractfactory.src;

public class MySQLCommand implements DBCommand {
    @Override
    public void execute(String query) {
        System.out.println("Executing MySQL query: " + query);
    }

    @Override
    public void beginTransaction() {
        System.out.println("Starting MySQL transaction...");
    }

    @Override
    public void commit() {
        System.out.println("Committing MySQL transaction...");
    }

    @Override
    public void rollback() {
        System.out.println("Rolling back MySQL transaction...");
    }
}
