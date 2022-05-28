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
            String nume = null;
            try{
                PrintWriter out1 = new PrintWriter(jucator1socket.getOutputStream(), true);
                Scanner in1 = new Scanner(jucator1socket.getInputStream());
                nume = in1.nextLine();
                out1.println("Buna ziua " + nume);
            }catch (Exception e) {
                System.out.println("error socket jucator 1"); }
            Player player1 = new Player(jucator1socket,nume);

            System.out.println("Asteptam jucator 2...");
            jucator2socket = serverSocket.accept();
            try{
                PrintWriter out2 = new PrintWriter(jucator2socket.getOutputStream(), true);
                Scanner in2 = new Scanner(jucator2socket.getInputStream());
                nume = in2.nextLine();
                out2.println("Buna ziua " + nume);
            }catch (Exception e) {
                System.out.println("error socket jucator 2"); }
            Player player2 = new Player(jucator2socket,nume);
            System.out.println("Avem 2 jucatori, let the games begin");
            new TicTacToe2(player1,player2);
        }
    }
}
