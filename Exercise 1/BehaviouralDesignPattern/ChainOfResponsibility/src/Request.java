package behaviouraldesignpattern.chainofresponsibility.src;

public class Request {
    public String user;
    public String data;

    public Request(String user, String data) {
        this.user = user;
        this.data = data;
    }
}
