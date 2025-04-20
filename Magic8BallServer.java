import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress; //8.1
import java.util.Random; // Hope this is okay!

// got help from https://www.geeksforgeeks.org/how-to-create-a-simple-tcp-client-server-connection-in-java/

public class Magic8BallServer {
    // list of responses from https://en.wikipedia.org/wiki/Magic_8_Ball
    private static final String[] answers = {
        // affirmative
        "It is certain",
        "It is decidedly so",
        "Without a doubt",
        "Yes definitely",
        "You may rely on it",
        "As I see it, yes",
        "Most likely",
        "Outlook good",
        "Yes",
        "Signs point to yes",
        // non-committal
        "Reply hazy, try again",
        "Ask again later",
        "Better not tell you now",
        "Cannot predict now",
        "Concentrate and ask again",
        // negative
        "Don't count on it",
        "My reply is no",
        "My sources say no",
        "Outlook not so good",
        "Very doubtful"
    };

    public static void main(String[] args) {
        // checking if port number is provided
        if (args.length != 1) {
            System.out.println("Provide a port number\n java Magic8BallServer <port number> &");
        }

        int portNum = Integer.parseInt(args[0]);

        // using try so it automatically closes the socket
        try(ServerSocket serverSocket = new ServerSocket(portNum)) {
            System.out.println("Using port: " + serverSocket.getInetAddress());

            while (true) {
                // client connection
                try (Socket clientSocket = serverSocket.accept()) {
                    // select random response
                    String response = getRandomAnswer();

                    // get ip address
                    String ipAddress = InetAddress.getLocalHost().getHostAddress();

                    String responseWithIp = response + "(" + ipAddress + ")";

                    // send response to client
                    OutputStream out = clientSocket.getOutputStream();
                    out.write(responseWithIp.getBytes());
                    out.flush();

                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not create server: " + e.getMessage());
        }

    }

    // random response generator
    private static String getRandomAnswer() {
        Random random = new Random();
        int index = random.nextInt(answers.length);
        return answers[index];
    }
}