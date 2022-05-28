package com.company;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    public PrintWriter out;
    public Scanner in;
    public String nume;
    public Player(Socket socket,String nume) {
        this.nume = nume;
        try{
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new Scanner(socket.getInputStream());
        }catch (Exception exp) {
            System.out.println("Erroareeeee!!!");
        }

    }
}
