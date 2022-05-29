package com.company;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Creeam o instana pentru fiecare jucator pentru a sti socketul prin care va comunica jocul cu el si numele lui.
 */
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
