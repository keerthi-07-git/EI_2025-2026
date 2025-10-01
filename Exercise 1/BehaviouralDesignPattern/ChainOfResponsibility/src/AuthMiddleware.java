package behaviouraldesignpattern.chainofresponsibility.src;

public class AuthMiddleware extends Middleware {
    @Override
    protected boolean check(Request req) {
        if (req.user == null) {
            System.out.println("Auth failed!");
            return false;
        }
        System.out.println("Auth ok");
        return true;
    }
}
