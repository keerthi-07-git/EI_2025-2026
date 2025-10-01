package structuraldesignpatterns.decorator.src;

public class StructuralDecorator {
    public static void main(String[] args) {
        DataStream stream = new EncryptDecorator(
                                new CompressDecorator(
                                    new FileStream()));
        System.out.println(stream.read());
    }
}
