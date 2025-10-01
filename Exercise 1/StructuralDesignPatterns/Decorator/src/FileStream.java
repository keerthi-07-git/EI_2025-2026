package structuraldesignpatterns.decorator.src;

public class FileStream implements DataStream {
    @Override
    public String read() {
        return "raw-data";
    }
}
