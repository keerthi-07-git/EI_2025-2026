package creationaldesignpatterns.builder.src;

public class HttpRequest {
    String url, method, body;

    private HttpRequest(Builder b) {
        url = b.url;
        method = b.method;
        body = b.body;
    }

    public static class Builder {
        String url, method, body;

        public Builder url(String u) { url = u; return this; }
        public Builder method(String m) { method = m; return this; }
        public Builder body(String b) { body = b; return this; }

        public HttpRequest build() { return new HttpRequest(this); }
    }

    @Override
    public String toString() {
        return method + " " + url + " Body:" + body;
    }
}
