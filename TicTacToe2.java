package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe2 implements ActionListener {
    JFrame frame = new JFrame();
    JFrame frame1 = new JFrame();
    JPanel t_panel = new JPanel();
    JPanel t_panel1 = new JPanel();
    JPanel bt_panel = new JPanel();
    JPanel bt_panel1 = new JPanel();
    JLabel textfield = new JLabel();
    JLabel textfield1 = new JLabel();
    JButton[] bton = new JButton[9];
    JButton[] bton1 = new JButton[9];
    int chance_flag = 0;
    Random random = new Random();
    boolean pl1_chance;

    public TicTacToe2(Player player1, Player player2) {
        {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 600, 100);
        bt_panel.setLayout(new GridLayout(3, 3));
        bt_panel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton();
            bt_panel.add(bton[i]);
            bton[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            bton[i].setFocusable(false);
            bton[i].addActionListener(this);
        }

            textfield.setBackground(new Color(100, 150, 124));
            textfield.setForeground(new Color(120, 205, 180));
            textfield.setFont(new Font("Ink Free", Font.BOLD, 50));
            textfield.setHorizontalAlignment(JLabel.CENTER);
            textfield.setText("Tic Tac Toe - " + player1.nume);
            textfield.setOpaque(true);

        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);
    }
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(600, 600);
        frame1.getContentPane().setBackground(new Color(50, 50, 50));
        frame1.setTitle("Tic Tac Toe");
        frame1.setLayout(new BorderLayout());
        frame1.setVisible(true);

        textfield1.setBackground(new Color(100, 150, 124));
        textfield1.setForeground(new Color(120, 205, 180));
        textfield1.setFont(new Font("Ink Free", Font.BOLD, 50));
        textfield1.setHorizontalAlignment(JLabel.CENTER);
        textfield1.setText("Tic Tac Toe - " + player2.nume);
        textfield1.setOpaque(true);
        t_panel1.setLayout(new BorderLayout());
        t_panel1.setBounds(0, 0, 800, 100);
        bt_panel1.setLayout(new GridLayout(3, 3));
        bt_panel1.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            bton1[i] = new JButton();
            bt_panel1.add(bton1[i]);
            bton1[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            bton1[i].setFocusable(false);
            bton1[i].addActionListener(this);
        }

        t_panel1.add(textfield1);
        frame1.add(t_panel1, BorderLayout.NORTH);
        frame1.add(bt_panel1);
        //player1.out.println("A mers");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
