package creationaldesignpatterns.builder.src;

public class CreationalBuilder {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest.Builder()
                .url("/api/data")
                .method("POST")
                .body("{id:1}")
                .build();
        System.out.println(req);
    }
}
