import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;

// got help from https://www.geeksforgeeks.org/how-to-create-a-simple-tcp-client-server-connection-in-java/

public class Magic8BallClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Use: Magic8BallClient <hostname> <port> <question>");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        // using try so it automatically closes the socket
        try (Socket socket = new Socket(hostname, port)) {
            // reading response from server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String response = reader.readLine();
            System.out.println("Magic 8 Ball says: " + response);
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
