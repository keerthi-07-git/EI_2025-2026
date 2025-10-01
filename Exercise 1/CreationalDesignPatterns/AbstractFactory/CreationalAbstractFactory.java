package creationaldesignpatterns.abstractfactory.src;

import java.util.Scanner;

public class CreationalAbstractFactory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Database Factory:");
        System.out.println("1. PostgreSQL");
        System.out.println("2. MySQL");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        DBFactory factory;
        if (choice == 1) {
            factory = new PostgresFactory();
            System.out.println("\n=== Using PostgreSQL Factory ===");
        } else if (choice == 2) {
            factory = new MySQLFactory();
            System.out.println("\n=== Using MySQL Factory ===");
        } else {
            System.out.println("Invalid choice! Defaulting to PostgreSQL.");
            factory = new PostgresFactory();
        }

        DBConnection conn = factory.createConnection();
        DBCommand cmd = factory.createCommand();

        conn.connect();
        cmd.beginTransaction();

        // Ask user for queries
        while (true) {
            System.out.print("Enter SQL command (or type 'exit' to quit): ");
            String query = sc.nextLine();
            if (query.equalsIgnoreCase("exit")) {
                break;
            } else if (query.equalsIgnoreCase("commit")) {
                cmd.commit();
            } else if (query.equalsIgnoreCase("rollback")) {
                cmd.rollback();
            } else {
                cmd.execute(query);
            }
        }

        System.out.println("Program finished.");
        sc.close();
    }
}

