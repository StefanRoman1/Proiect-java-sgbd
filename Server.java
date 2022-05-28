package com.company;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class Server {

    public static boolean playing = false;
    public static int waiting = 0;


    public static void main(String[] args) throws IOException {
        final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("Server started...");
        System.out.println("Wating for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client!");
            clientSocket.setSoTimeout(60000);
            Thread t = new Thread() {
                public void run() {
                    try (
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            Scanner in = new Scanner(clientSocket.getInputStream());
                    ) {
                        while (in.hasNextLine()) {
                            System.out.println(waiting);
                            String input = in.nextLine();
                            System.out.println(input);
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
                                    playing = true;
                                    out.println("Match started!");
                                }
                                else
                                {
                                    out.println("Two players are already playing!");
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
