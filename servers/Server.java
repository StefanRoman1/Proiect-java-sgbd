package servers;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Serverul la care se conecteaza toti clientii in mod concurent
 */
public class Server {
    public static int waiting = 0;

    /**
     * Ruland mainul deschidem serverul pentru conectarea clientilor.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Server started...");
        System.out.println("Wating for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client!");
            Thread t = new Thread() {
                public void run() {
                    try (
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            Scanner in = new Scanner(clientSocket.getInputStream());
                    ) {
                        while (in.hasNextLine()) {
                            String input = in.nextLine();
                            if (input.equalsIgnoreCase("exit")) {
                                System.out.println("Client disconnected!");
                                break;
                            }
                            if(input.equalsIgnoreCase("play"))
                            {
                                if(waiting%2 == 0)
                                {
                                    waiting++;
                                    out.println("Waiting for another player...");
                                }
                                else
                                if(waiting%2 == 1)
                                {
                                    waiting++;
                                    out.println("Match started!");
                                }
                            }
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
    }
}
