package behaviouraldesignpattern.chainofresponsibility.src;

public class BehaviouralChain {
    public static void main(String[] args) {
        Request r = new Request("keerthi", "upload-file");

        Middleware auth = new AuthMiddleware();
        Middleware log = new LogMiddleware();

        auth.linkWith(log);
        auth.process(r);
    }
}
