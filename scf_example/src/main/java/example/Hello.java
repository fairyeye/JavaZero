package example;

public class Hello {
    public String mainHandler(KeyValueClass kv) {
        MessageServiceImpl service = new MessageServiceImpl();
        service.sendMessage();
        return String.format("Hello World");
    }

    public static void main(String[] args) {
        MessageServiceImpl service = new MessageServiceImpl();
        service.sendMessage();
    }
}