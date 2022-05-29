package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Jocul propriu-zis
 */

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
    boolean playerTurn = true;
    public int mutari = 0;
    public Player player1;
    public Player player2;

    /**
     * Aici se creeaza cele doua interfete grafice pentru cei doi utilizatori
     * @param player1
     * @param player2
     */
    public TicTacToe2(Player player1, Player player2) {
        {
            this.player1 = player1;
            this.player2 = player2;
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.getContentPane().setBackground(new Color(50, 50, 50));
            frame.setTitle(player1.nume + " - side");
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
            textfield.setText("It`s " + player1.nume + "`s turn!");
            textfield.setOpaque(true);

            t_panel.add(textfield);
            frame.add(t_panel, BorderLayout.NORTH);
            frame.add(bt_panel);
        }

        {
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setSize(600, 600);
            frame1.getContentPane().setBackground(new Color(50, 50, 50));
            frame1.setTitle(player2.nume + " - side");
            frame1.setLayout(new BorderLayout());
            frame1.setVisible(true);

            textfield1.setBackground(new Color(100, 150, 124));
            textfield1.setForeground(new Color(120, 205, 180));
            textfield1.setFont(new Font("Ink Free", Font.BOLD, 50));
            textfield1.setHorizontalAlignment(JLabel.CENTER);
            textfield1.setText("It`s " + player1.nume + "`s turn!");
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
        }

    }

    /**
     * Jocul se va termina cand unul din jucatori a castigat sau cand este declarata remiza, moment in care vor primi un anumit punctaj in functie de finalul
     * jocului.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(playerTurn)
        {
            for(int i = 0; i < 9; i++)
            {
                if(e.getSource() == bton[i] && bton[i].getText() == "")
                {
                    bton[i].setBackground(Color.CYAN);
                    bton[i].setText("X");
                    bton1[i].setBackground(Color.CYAN);
                    bton1[i].setText("X");
                    mutari++;
                    check_move();
                    playerTurn = false;
                    textfield1.setText("It`s " + player2.nume + "`s turn!");
                    textfield.setText("It`s " + player2.nume + "`s turn!");
                    break;
                }
            }

        }
        else
        {
            for(int i = 0; i < 9; i++)
            {
                if(e.getSource() == bton1[i] && bton1[i].getText()=="")
                {
                    bton1[i].setBackground(Color.RED);
                    bton1[i].setText("O");
                    bton[i].setBackground(Color.RED);
                    bton[i].setText("O");
                    mutari++;
                    check_move();
                    playerTurn = true;
                    textfield1.setText("It`s " + player1.nume + "`s turn!");
                    textfield.setText("It`s " + player1.nume + "`s turn!");
                    break;
                }
            }
        }
    }

    /**
     * Verificam daca exista 3 piese de acelasi fel aliniate
     */
    public void check_move() {
        if ((bton[0].getText() == "X") && (bton[1].getText() == "X") && (bton[2].getText() == "X")) {
            Win(0, 1, 2);
        }
        else if ((bton[0].getText() == "X") && (bton[4].getText() == "X") && (bton[8].getText() == "X")) {
            Win(0, 4, 8);
        }
        else if ((bton[0].getText() == "X") && (bton[3].getText() == "X") && (bton[6].getText() == "X")) {
            Win(0, 3, 6);
        }
        else if ((bton[1].getText() == "X") && (bton[4].getText() == "X") && (bton[7].getText() == "X")) {
            Win(1, 4, 7);
        }
        else if ((bton[2].getText() == "X") && (bton[4].getText() == "X") && (bton[6].getText() == "X")) {
            Win(2, 4, 6);
        }
        else if ((bton[2].getText() == "X") && (bton[5].getText() == "X") && (bton[8].getText() == "X")) {
            Win(2, 5, 8);
        }
        else if ((bton[3].getText() == "X") && (bton[4].getText() == "X") && (bton[5].getText() == "X")) {
            Win(3, 4, 5);
        }
        else if ((bton[6].getText() == "X") && (bton[7].getText() == "X") && (bton[8].getText() == "X")) {
            Win(6, 7, 8);
        }
        else if ((bton1[0].getText() == "O") && (bton1[1].getText() == "O") && (bton1[2].getText() == "O")) {
            Win(0, 1, 2);
        }
        else if ((bton1[0].getText() == "O") && (bton1[3].getText() == "O") && (bton1[6].getText() == "O")) {
            Win(0, 3, 6);
        }
        else if ((bton1[0].getText() == "O") && (bton1[4].getText() == "O") && (bton1[8].getText() == "O")) {
            Win(0, 4, 8);
        }
        else if ((bton1[1].getText() == "O") && (bton1[4].getText() == "O") && (bton1[7].getText() == "O")) {
            Win(1, 4, 7);
        }
        else if ((bton1[2].getText() == "O") && (bton1[4].getText() == "O") && (bton1[6].getText() == "O")) {
            Win(2, 4, 6);
        }
        else if ((bton1[2].getText() == "O") && (bton1[5].getText() == "O") && (bton1[8].getText() == "O")) {
            Win(2, 5, 8);
        }
        else if ((bton1[3].getText() == "O") && (bton1[4].getText() == "O") && (bton1[5].getText() == "O")) {
            Win(3, 4, 5);
        } else if ((bton1[6].getText() == "O") && (bton1[7].getText() == "O") && (bton1[8].getText() == "O")) {
            Win(6, 7, 8);
        }
       else if(mutari==9) {
            textfield.setText("Match Tie");
            textfield1.setText("Match Tie");
            EndGame("Draw");
        }
    }

    /**
     * Daca a fost declarat un castigator, marcam linia prin care a fost castigat jocul
     * @param x1
     * @param x2
     * @param x3
     */
    public void Win(int x1, int x2, int x3)
    {
        bton[x1].setBackground(Color.YELLOW);
        bton[x2].setBackground(Color.YELLOW);
        bton[x3].setBackground(Color.YELLOW);
        bton1[x1].setBackground(Color.YELLOW);
        bton1[x2].setBackground(Color.YELLOW);
        bton1[x3].setBackground(Color.YELLOW);
        if(playerTurn)
        {
            textfield.setText(player1.nume + " WON ");
            textfield1.setText(player1.nume + " WON ");
            EndGame(player1.nume);
        }
        else
        {
            textfield.setText(player2.nume + " WON ");
            textfield1.setText(player2.nume + " WON ");
            EndGame(player2.nume);
        }
    }

    /**
     * Inchidem jocul si trimitem pe socketul fiecarui jucator rezultatul final
     * @param s
     */
    public void EndGame(String s)
    {
        frame.dispose();
        frame1.dispose();
        if(s.equals("Draw"))
        {
            player1.out.println("Meciul este remiza!");
            player2.out.println("Meciul este remiza!");
        }
        else
        {
            player1.out.println("A castigat " + s + " !");
            player2.out.println("A castigat " + s + " !");
        }
    }
}
