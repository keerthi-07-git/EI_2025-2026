package behaviouraldesignpattern.chainofresponsibility.src;

public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware n) {
        next = n;
        return n;
    }

    public void process(Request req) {
        if (check(req) && next != null) {
            next.process(req);
        }
    }

    protected abstract boolean check(Request req);
}
