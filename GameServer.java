package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class GameServer extends JFrame {
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
                Scanner in1 = new Scanner(jucator1socket.getInputStream());
                nume = in1.nextLine();
            }catch (Exception e) {
                e.printStackTrace();
            }
            Player player1 = new Player(jucator1socket,nume);

            System.out.println("Asteptam jucator 2...");
            jucator2socket = serverSocket.accept();
            try{
                Scanner in2 = new Scanner(jucator2socket.getInputStream());
                nume = in2.nextLine();
            }catch (Exception e) {
                e.printStackTrace();
            }
            Player player2 = new Player(jucator2socket,nume);
            new TicTacToe2(player1,player2);
        }
    }
}
