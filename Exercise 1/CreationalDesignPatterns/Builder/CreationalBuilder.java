package creationaldesignpatterns.builder.src;

import java.util.Scanner;

public class CreationalBuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter URL: ");
        String url = sc.nextLine();

        System.out.print("Enter HTTP Method (GET, POST, PUT, DELETE): ");
        String method = sc.nextLine().toUpperCase();

        String body = null;
        if (!method.equals("GET")) { // usually GET doesn’t have body
            System.out.print("Enter Request Body (or leave blank): ");
            body = sc.nextLine();
        }

        HttpRequest req = new HttpRequest.Builder()
                .url(url)
                .method(method)
                .body(body)
                .build();

        System.out.println("\n✅ Built Request:");
        System.out.println(req);

        sc.close();
    }
}
