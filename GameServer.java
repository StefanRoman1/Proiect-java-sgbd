package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Serverul la care se conecteaza utilizatorii deja conectati la aplicatie pentru a se juca un meci.
 */
public class GameServer extends JFrame {
    public static JTextArea text;

    /**
     * Ruland mainul, acesta asteapta sa intre 2 utilizatori pentru a deschide un joc pentru cei 2.
     * @param args
     * @throws IOException
     */

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
