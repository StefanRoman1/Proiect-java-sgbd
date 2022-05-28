package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer extends JFrame {
    public static Container c;
    public static JTextArea text;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2000);
        Socket jucator1socket = null;
        Socket jucator2socket = null;

        while(true)
        {
            System.out.println("Asteptam jucator 1...");
            jucator1socket = serverSocket.accept();
            try{
                PrintWriter out = new PrintWriter(jucator1socket.getOutputStream(), true);
                Scanner in = new Scanner(jucator1socket.getInputStream());
                in.nextLine();
                out.println("Buna ziua jucator 1");
            }catch (Exception e) {
                System.out.println("error socket jucator 1"); }
            Player player1 = new Player(jucator1socket);

            System.out.println("Asteptam jucator 2...");
            jucator2socket = serverSocket.accept();
            Player player2 = new Player(jucator2socket);
            System.out.println("Avem 2 jucatori, let the games begin");
            /*Thread thread  = new Thread() {
                public void run() {
                    new TicTacToe(player1,player2);
                }
            };
            thread.start();*/
            new TicTacToe(player1,player2);
        }
    }
}
