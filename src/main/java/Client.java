import java.io.*;
import java.net.Socket;

public class Client {
    private static File settingsFile = new File("Settings.txt");
    public BufferedReader br;
    public DataOutputStream out;
    public BufferedReader in;
    private String host;
    private int port;
    private String nickname;
    private Logger logger = Logger.get();
    private Socket socket;

    public Client(String host) {
        this.host = host;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(settingsFile))) {
                String line = br.readLine();
                port = Integer.parseInt(line.substring(5, line.length()));
            }
            socket = new Socket(host, port);
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void connect() {
        try {
            System.out.println("Client connected to socket.");
            System.out.println("Please choose your nickname");
            String clientCommand = br.readLine();
            out.writeUTF(clientCommand);
            nickname = clientCommand;
            out.flush();
            while (!socket.isOutputShutdown()) {
                while (in.ready()) {
                    logger.log(in.readLine().toString());
                }
                if (br.ready()) {
                    clientCommand = br.readLine();
                    out.writeUTF(clientCommand);
                    out.flush();
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

