package behaviouraldesignpattern.chainofresponsibility.src;

public class LogMiddleware extends Middleware {
    @Override
    protected boolean check(Request req) {
        System.out.println("Log: " + req.data);
        return true;
    }
}
