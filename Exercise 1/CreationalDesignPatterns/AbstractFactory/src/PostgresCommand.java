package creationaldesignpatterns.abstractfactory.src;

public class PostgresCommand implements DBCommand {
    @Override
    public void execute(String query) {
        System.out.println("Executing PostgreSQL query: " + query);
    }

    @Override
    public void beginTransaction() {
        System.out.println("Starting PostgreSQL transaction...");
    }

    @Override
    public void commit() {
        System.out.println("Committing PostgreSQL transaction...");
    }

    @Override
    public void rollback() {
        System.out.println("Rolling back PostgreSQL transaction...");
    }
}
