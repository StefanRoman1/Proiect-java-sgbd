package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Meniu extends JFrame implements ActionListener {

    public Container c;
    private JLabel title;
    private JButton find;
    private JButton logati;
    private JButton exit;
    private JTextArea tout;
    final String HOST = "127.0.0.1";
    final int PORT = 4040;
    Socket socket = new Socket(HOST, PORT);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    Scanner in = new Scanner(socket.getInputStream());
    public String nume;

    public Meniu(String nume) throws IOException {
        this.nume = nume;
        setTitle("Game");
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Meniu");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(250, 30);
        c.add(title);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(250, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);


        find = new JButton("Find game");
        find.setFont(new Font("Arial", Font.PLAIN, 15));
        find.setSize(100, 20);
        find.setLocation(100, 200);
        find.addActionListener(this);
        c.add(find);

        logati = new JButton("Users");
        logati.setFont(new Font("Arial", Font.PLAIN, 15));
        logati.setSize(100, 20);
        logati.setLocation(100, 300);
        logati.addActionListener(this);
        c.add(logati);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100, 20);
        exit.setLocation(100, 400);
        exit.addActionListener(this);
        c.add(exit);

        setVisible(true);

        System.out.println("Welcome " + nume);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == find)
        {
            out.println("play");
            String input = in.nextLine();
            tout.setText(input);
            if(input.equals("Waiting for another player..."))
            {
                //this.dispose();
                //new Game();
                try {
                    Socket socket = new Socket(HOST, 2000);
                    PrintWriter gout = new PrintWriter(socket.getOutputStream(), true);
                    Scanner gin = new Scanner(socket.getInputStream());
                    gout.println(nume);
                    System.out.println(gin.nextLine());
                }catch (Exception ex) {
                    System.out.println("eroare aiaie");
                }
            }
            else if (input.equals("Match started!"))
            {
                try {
                    Socket socket = new Socket(HOST, 2000);
                    PrintWriter gout1 = new PrintWriter(socket.getOutputStream(), true);
                    Scanner gin1 = new Scanner(socket.getInputStream());
                    gout1.println(nume);
                    System.out.println(gin1.nextLine());
                }catch (Exception ex) {System.out.println("eroare aiaie2");}
            }
        }
        else if(e.getSource() == logati)
        {
            tout.setText("");
            try {
                Statement stmt = Main.db.createStatement();
                ResultSet rs=stmt.executeQuery("select * from LOGARI");
                while(rs.next())
                {
                    String aux = rs.getString(3);
                    tout.append(aux);
                    tout.append(" : ");
                    tout.append(rs.getString(5));
                    tout.append("\n");
                   //System.out.println(aux);
                }
                //tout.setText(String.valueOf(oameni));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else
        {
            out.println("exit");
            Statement alter = null;
            try {
                alter = Main.db.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String s = "UPDATE LOGARI SET STATE = 'offline' WHERE NICKNAME = " + "'" + nume + "'";
            try {
                alter.executeQuery(s);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
    }
}
