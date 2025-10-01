package structuraldesignpatterns.decorator.src;

public class EncryptDecorator implements DataStream {
    private final DataStream stream;

    public EncryptDecorator(DataStream s) {
        stream = s;
    }

    @Override
    public String read() {
        return "encrypted(" + stream.read() + ")";
    }
}
