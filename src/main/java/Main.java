import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("localhost");
        client.connect();

    }
}

