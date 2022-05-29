package com.company;

import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    public PrintWriter out;
    public String nume;
    public Player(Socket socket,String nume) {
        this.nume = nume;
        try{
            out = new PrintWriter(socket.getOutputStream(),true);
        }catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
