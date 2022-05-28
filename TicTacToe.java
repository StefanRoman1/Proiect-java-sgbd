package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    public JFrame frame1 = new JFrame();
    public JFrame frame2 = new JFrame();
    public Container c = new Container();
    public Container c1 = new Container();
    public JLabel title;
    public JLabel title1;
    public JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    public JButton b11,b22,b33,b44,b55,b66,b77,b88,b99;
    public JButton EXIT;
    public JButton EXIT1;
    public int playerTurn = 0;


    public TicTacToe(Player player1, Player player2) {
        //setTitle("Tic Tac Toe");
        //setBounds(300, 90, 600, 600);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        frame1.getContentPane();
        frame2.getContentPane();
        {
            //c = getContentPane();
            c.setLayout(null);

            title = new JLabel("Tic tac toe");
            title.setFont(new Font("Arial", Font.PLAIN, 30));
            title.setSize(300, 30);
            title.setLocation(180, 30);
            c.add(title);

            EXIT = new JButton("EXIT");
            EXIT.setFont(new Font("Arial", Font.PLAIN, 30));
            EXIT.setSize(100, 30);
            EXIT.setLocation(300, 300);
            EXIT.addActionListener(this);
            c.add(EXIT);

            b1 = new JButton("");
            b1.setFont(new Font("Arial", Font.PLAIN, 30));
            b1.setSize(30, 30);
            b1.setLocation(100, 100);
            b1.addActionListener(this);
            c.add(b1);

            b2 = new JButton("");
            b2.setFont(new Font("Arial", Font.PLAIN, 30));
            b2.setSize(30, 30);
            b2.setLocation(100, 130);
            b2.addActionListener(this);
            c.add(b2);

            b3 = new JButton("");
            b3.setFont(new Font("Arial", Font.PLAIN, 30));
            b3.setSize(30, 30);
            b3.setLocation(100, 160);
            b3.addActionListener(this);
            c.add(b3);

            b4 = new JButton("");
            b4.setFont(new Font("Arial", Font.PLAIN, 30));
            b4.setSize(30, 30);
            b4.setLocation(130, 160);
            b4.addActionListener(this);
            c.add(b4);

            b5 = new JButton("");
            b5.setFont(new Font("Arial", Font.PLAIN, 30));
            b5.setSize(30, 30);
            b5.setLocation(130, 130);
            b5.addActionListener(this);
            c.add(b5);

            b6 = new JButton("");
            b6.setFont(new Font("Arial", Font.PLAIN, 30));
            b6.setSize(30, 30);
            b6.setLocation(130, 100);
            b6.addActionListener(this);
            c.add(b6);

            b7 = new JButton("");
            b7.setFont(new Font("Arial", Font.PLAIN, 30));
            b7.setSize(30, 30);
            b7.setLocation(160, 100);
            b7.addActionListener(this);
            c.add(b7);

            b8 = new JButton("");
            b8.setFont(new Font("Arial", Font.PLAIN, 30));
            b8.setSize(30, 30);
            b8.setLocation(160, 130);
            b8.addActionListener(this);
            c.add(b8);

            b9 = new JButton("");
            b9.setFont(new Font("Arial", Font.PLAIN, 30));
            b9.setSize(30, 30);
            b9.setLocation(160, 160);
            b9.addActionListener(this);
            c.add(b9);
        }
        {
            //c1 = getContentPane();
            c1.setLayout(null);

            title1 = new JLabel("Tic tac toe");
            title1.setFont(new Font("Arial", Font.PLAIN, 30));
            title1.setSize(300, 30);
            title1.setLocation(180, 30);
            c1.add(title1);

            EXIT1 = new JButton("EXIT");
            EXIT1.setFont(new Font("Arial", Font.PLAIN, 30));
            EXIT1.setSize(100, 30);
            EXIT1.setLocation(300, 300);
            EXIT1.addActionListener(this);
            c1.add(EXIT1);

            b11 = new JButton("");
            b11.setFont(new Font("Arial", Font.PLAIN, 30));
            b11.setSize(30, 30);
            b11.setLocation(100, 100);
            b11.addActionListener(this);
            c1.add(b11);

            b22 = new JButton("");
            b22.setFont(new Font("Arial", Font.PLAIN, 30));
            b22.setSize(30, 30);
            b22.setLocation(100, 130);
            b22.addActionListener(this);
            c1.add(b22);

            b33 = new JButton("");
            b33.setFont(new Font("Arial", Font.PLAIN, 30));
            b33.setSize(30, 30);
            b33.setLocation(100, 160);
            b33.addActionListener(this);
            c1.add(b33);

            b44 = new JButton("");
            b44.setFont(new Font("Arial", Font.PLAIN, 30));
            b44.setSize(30, 30);
            b44.setLocation(130, 160);
            b44.addActionListener(this);
            c1.add(b44);

            b55 = new JButton("");
            b55.setFont(new Font("Arial", Font.PLAIN, 30));
            b55.setSize(30, 30);
            b55.setLocation(130, 130);
            b55.addActionListener(this);
            c1.add(b55);

            b66 = new JButton("");
            b66.setFont(new Font("Arial", Font.PLAIN, 30));
            b66.setSize(30, 30);
            b66.setLocation(130, 100);
            b66.addActionListener(this);
            c1.add(b66);

            b77 = new JButton("");
            b77.setFont(new Font("Arial", Font.PLAIN, 30));
            b77.setSize(30, 30);
            b77.setLocation(160, 100);
            b77.addActionListener(this);
            c1.add(b77);

            b88 = new JButton("");
            b88.setFont(new Font("Arial", Font.PLAIN, 30));
            b88.setSize(30, 30);
            b88.setLocation(160, 130);
            b88.addActionListener(this);
            c1.add(b88);

            b99 = new JButton("");
            b99.setFont(new Font("Arial", Font.PLAIN, 30));
            b99.setSize(30, 30);
            b99.setLocation(160, 160);
            b99.addActionListener(this);
            c1.add(b99);
        }
        //frame1.getContentPane();
        //
        frame1.add(c);
        frame1.setSize(600,600);
        frame1.setTitle("Tic Tac Toe - client 1");
        frame1.setVisible(true);

        frame2.add(c1);


        frame2.setSize(600,600);

        frame2.setTitle("Tic Tac Toe - client 2");


        frame2.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1 || e.getSource() == b11)
        {
            b1.setBackground(Color.green);
            b11.setBackground(Color.green);
            System.out.println("9");
        }
        else
        if(e.getSource() == b2 || e.getSource() == b22)
        {
            b2.setBackground(Color.red);
            b22.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b3 || e.getSource() == b33)
        {
            b3.setBackground(Color.red);
            b33.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b4 || e.getSource() == b44)
        {
            b4.setBackground(Color.red);
            b44.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b5 || e.getSource() == b55)
        {
            b5.setBackground(Color.red);
            b55.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b6 || e.getSource() == b66)
        {
            b6.setBackground(Color.red);
            b66.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b7 || e.getSource() == b77)
        {
            b7.setBackground(Color.red);
            b77.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b8 || e.getSource() == b88)
        {
            b8.setBackground(Color.red);
            b88.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == b9 || e.getSource() == b99)
        {
            b9.setBackground(Color.red);
            b99.setBackground(Color.red);
            System.out.println("9");
        }
        else
        if(e.getSource() == EXIT || e.getSource() == EXIT1)
        {
            frame1.dispose();
            frame2.dispose();
        }
    }
}
