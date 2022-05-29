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

/**
 * Dupa ce ne-am logat, utilizatorul va intra in meniu
 */

public class Meniu extends JFrame implements ActionListener {

    public Container c;
    private JLabel title;
    private JButton find;
    private JButton logati;
    private JButton exit;
    private JButton top;
    private JTextArea tout;
    final String HOST = "127.0.0.1";
    final int PORT = 4040;
    Socket socket = new Socket(HOST, PORT);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    Scanner in = new Scanner(socket.getInputStream());
    public String nume;

    /**
     * Interfata grafica a meniului
     * @param nume
     * @throws IOException
     */
    public Meniu(String nume) throws IOException {
        this.nume = nume;
        setTitle("Game");
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Meniu - " + nume);
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
        find.setLocation(100, 100);
        find.addActionListener(this);
        c.add(find);

        top = new JButton(("Top"));
        top.setFont(new Font("Arial", Font.PLAIN, 15));
        top.setSize(100,20);
        top.setLocation(100,200);
        top.addActionListener(this);
        c.add(top);

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
    }

    /**
     * Butonul find il va conecta la serverul de joc, unde va astepta un alt jucator sau va intra in meci daca un jucator
     * era deja in asteptare.
     * Butonul top va arata clasamentul utilizatorilor in ordine descrescatoare.
     * Butonul users va arata utilizatorii logati la momentul actual.
     * Butonul exit va deconecta utilizatorul.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == find)
        {
            out.println("play");
            String input = in.nextLine();
            tout.setText(input);
            if(input.equals("Waiting for another player..."))
            {
                try {
                    Socket socket = new Socket(HOST, 2000);
                    PrintWriter gout = new PrintWriter(socket.getOutputStream(), true);
                    Scanner gin = new Scanner(socket.getInputStream());
                    gout.println(nume);
                    String raspServer = null;
                    raspServer = gin.nextLine();
                    tout.setText(raspServer);
                    System.out.println(raspServer);
                    if(raspServer.contains("remiza"))
                    {
                        CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.draw(?)}");
                        stmt.setString(1,nume);
                        stmt.execute();
                        System.out.println("A intrat pe remiza");
                    }
                    else
                    {
                        if(raspServer.contains(nume))
                        {
                            CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.win(?)}");
                            stmt.setString(1,nume);
                            stmt.execute();
                        }
                        else
                        {
                            CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.lost(?)}");
                            stmt.setString(1,nume);
                            stmt.execute();

                        }
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (input.equals("Match started!"))
            {
                try {
                    Socket socket = new Socket(HOST, 2000);
                    PrintWriter gout1 = new PrintWriter(socket.getOutputStream(), true);
                    Scanner gin1 = new Scanner(socket.getInputStream());
                    gout1.println(nume);
                    String raspServer = null;
                    raspServer = gin1.nextLine();
                    tout.setText(raspServer);
                    System.out.println(raspServer);
                    if(raspServer.contains("remiza"))
                    {
                        CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.draw(?)}");
                        stmt.setString(1,nume);
                        stmt.execute();
                    }
                    else
                    {
                        if(raspServer.contains(nume))
                        {
                            CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.win(?)}");
                            stmt.setString(1,nume);
                            stmt.execute();
                        }
                        else
                        {
                            CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.lost(?)}");
                            stmt.setString(1,nume);
                            stmt.execute();
                        }
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getSource() == logati)
        {
            tout.setText("");
            try {
                Statement stmt = Main.db.createStatement();
                ResultSet rs=stmt.executeQuery("select * from LOGARI WHERE STATE = 'online'");
                while(rs.next()) {
                    String aux = rs.getString(3);
                    tout.append(aux);
                    tout.append(" : ");
                    tout.append(rs.getString(5));
                    tout.append("\n");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else
        if(e.getSource() == top)
        {
            tout.setText("");
            try {
                Statement stmt = Main.db.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from LOGARI order by PUNCTE DESC");
                while (rs.next()) {
                    tout.append(rs.getString(3));
                    tout.append(" : ");
                    tout.append(String.valueOf(rs.getInt(6)));
                    tout.append("\n");
                }
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        else
        {
            out.println("exit");
            try {
                CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.disconnect(?)}");
                stmt.setString(1,nume);
                stmt.execute();
            }catch(Exception expe){
                expe.printStackTrace();
            }
            this.dispose();
        }
    }
}
