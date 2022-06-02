package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Formularul de inregistrare unde un utilizator isi poate creea un cont.
 */

public class RegisterForm extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel fname;
    private JTextField tfname;
    private JLabel lname;
    private JTextField tlname;
    private JLabel password;
    private JTextField tpassword;
    private JLabel repassword;
    private JTextField trepassword;
    private JLabel nickname;
    private JTextField tnickname;
    private JButton register;
    private JButton exit;
    private JLabel res;
    public LoginForm loginForm;

    /**
     * Interfata grafica a formularului
     */
    public RegisterForm() {

        setTitle("Register Form");
        setBounds(600, 100, 400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        fname = new JLabel("First Name");
        fname.setFont(new Font("Arial", Font.PLAIN, 20));
        fname.setSize(100, 20);
        fname.setLocation(150, 40);
        fname.setForeground(Color.getHSBColor(12.7f,50f,40f));
        c.add(fname);

        tfname = new JTextField();
        tfname.setFont(new Font("Arial", Font.PLAIN, 15));
        tfname.setSize(190, 20);
        tfname.setLocation(100, 70);
        tfname.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tfname.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tfname);

        lname = new JLabel("Last Name");
        lname.setFont(new Font("Arial", Font.PLAIN, 20));
        lname.setForeground(Color.getHSBColor(12.7f,50f,40f));
        lname.setSize(100, 20);
        lname.setLocation(150, 100);
        c.add(lname);

        tlname = new JTextField();
        tlname.setFont(new Font("Arial", Font.PLAIN, 15));
        tlname.setSize(190, 20);
        tlname.setLocation(100, 130);
        tlname.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tlname.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tlname);

        nickname = new JLabel("Nickname");
        nickname.setFont(new Font("Arial", Font.PLAIN, 20));
        nickname.setSize(100, 20);
        nickname.setLocation(150, 160);
        nickname.setForeground(Color.getHSBColor(12.7f,50f,40f));
        c.add(nickname);

        tnickname = new JTextField();
        tnickname.setFont(new Font("Arial", Font.PLAIN, 15));
        tnickname.setSize(190, 20);
        tnickname.setLocation(100, 190);
        tnickname.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tnickname.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tnickname);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(150, 220);
        password.setForeground(Color.getHSBColor(12.7f,50f,40f));
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(100, 250);
        tpassword.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tpassword.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tpassword);

        repassword = new JLabel("Retype password");
        repassword.setFont(new Font("Arial", Font.PLAIN, 20));
        repassword.setSize(100, 20);
        repassword.setLocation(150, 280);
        repassword.setForeground(Color.getHSBColor(12.7f,50f,40f));
        c.add(repassword);

        trepassword = new JTextField();
        trepassword.setFont(new Font("Arial", Font.PLAIN, 15));
        trepassword.setSize(190, 20);
        trepassword.setLocation(100, 310);
        trepassword.setBackground(Color.getHSBColor(12.7f,50f,40f));
        trepassword.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(trepassword);

        register = new JButton("Submit");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(150, 360);
        register.addActionListener(this);
        register.setBackground(Color.getHSBColor(12.7f,50f,40f));
        register.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(register);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100,20);
        exit.setLocation(150,400);
        exit.addActionListener(this);
        exit.setBackground(Color.getHSBColor(12.7f,50f,40f));
        exit.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(exit);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(140, 450);
        res.setForeground(Color.getHSBColor(12.7f,50f,40f));
        c.add(res);

        setVisible(true);
        c.setBackground(Color.getHSBColor(15.5f,55f,60f));
    }

    /**
     * Aici verificam ca utilizatorul sa nu lase spatii necompletate si ca parola si verificarea ei sa coincida.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit)
        {
            this.dispose();
            loginForm = new LoginForm();
        }
        else{
            if(tpassword.getText().equals(trepassword.getText()))
            {
                res.setText("Registered!");
                try {
                    CallableStatement stmt = Main.db.prepareCall("{call proiect_sgbd.register(?,?,?,?)}");
                    stmt.setString(1,tlname.getText());
                    stmt.setString(2,tfname.getText());
                    stmt.setString(3,tnickname.getText());
                    stmt.setString(4,tpassword.getText());
                    stmt.execute();
                    this.dispose();
                    loginForm = new LoginForm();
                } catch (SQLException ex) {
                    res.setText("Empty fields!");
                }
            }
            else {
                res.setText("Passwords do not match!");
            }
        }
    }
}
