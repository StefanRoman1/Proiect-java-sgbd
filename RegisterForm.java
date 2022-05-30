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
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Register Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(180, 30);
        c.add(title);

        fname = new JLabel("First Name");
        fname.setFont(new Font("Arial", Font.PLAIN, 20));
        fname.setSize(100, 20);
        fname.setLocation(100, 100);
        c.add(fname);

        tfname = new JTextField();
        tfname.setFont(new Font("Arial", Font.PLAIN, 15));
        tfname.setSize(190, 20);
        tfname.setLocation(200, 100);
        c.add(tfname);

        lname = new JLabel("Last Name");
        lname.setFont(new Font("Arial", Font.PLAIN, 20));
        lname.setSize(100, 20);
        lname.setLocation(100, 150);
        c.add(lname);

        tlname = new JTextField();
        tlname.setFont(new Font("Arial", Font.PLAIN, 15));
        tlname.setSize(190, 20);
        tlname.setLocation(200, 150);
        c.add(tlname);

        nickname = new JLabel("Nickname");
        nickname.setFont(new Font("Arial", Font.PLAIN, 20));
        nickname.setSize(100, 20);
        nickname.setLocation(100, 200);
        c.add(nickname);

        tnickname = new JTextField();
        tnickname.setFont(new Font("Arial", Font.PLAIN, 15));
        tnickname.setSize(190, 20);
        tnickname.setLocation(200, 200);
        c.add(tnickname);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 250);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(200, 250);
        c.add(tpassword);

        repassword = new JLabel("Retype password");
        repassword.setFont(new Font("Arial", Font.PLAIN, 20));
        repassword.setSize(100, 20);
        repassword.setLocation(100, 300);
        c.add(repassword);

        trepassword = new JTextField();
        trepassword.setFont(new Font("Arial", Font.PLAIN, 15));
        trepassword.setSize(190, 20);
        trepassword.setLocation(200, 300);
        c.add(trepassword);

        register = new JButton("Submit");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(150, 450);
        register.addActionListener(this);
        c.add(register);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100,20);
        exit.setLocation(270,450);
        exit.addActionListener(this);
        c.add(exit);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(200, 500);
        c.add(res);

        setVisible(true);
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
