package structuraldesignpatterns.decorator.src;

public class CompressDecorator implements DataStream {
    private final DataStream stream;

    public CompressDecorator(DataStream s) {
        stream = s;
    }

    @Override
    public String read() {
        return "compressed(" + stream.read() + ")";
    }
}
