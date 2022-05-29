package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener{

    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel password;
    private JTextField tpassword;
    private JButton login;
    private JButton register;
    private JLabel res;
    public RegisterForm registerForm;
    public Meniu meniu;

    public LoginForm() {
        setTitle("Login Form");
        setBounds(300, 90, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Login Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(180, 30);
        c.add(title);

        name = new JLabel("Nickname");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 200);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 20);
        tpassword.setLocation(200, 200);
        c.add(tpassword);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(150, 450);
        login.addActionListener(this);
        c.add(login);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(270, 450);
        register.addActionListener(this);
        c.add(register);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(150, 500);
        c.add(res);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == login)
        {
            try {
                CallableStatement stmt = Main.db.prepareCall("{? = call proiect_sgbd.login(?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(2,tname.getText());
                stmt.setString(3,tpassword.getText());
                stmt.execute();
                String result = stmt.getString(1);
                if(result.equals("fail"))
                {
                    res.setText("Username or password incorrect");
                }
                else
                {
                    CallableStatement stmt1 = Main.db.prepareCall("{? = call proiect_sgbd.logg_in_check(?)}");
                    stmt1.registerOutParameter(1, Types.VARCHAR);
                    stmt1.setString(2,tname.getText());
                    stmt1.execute();
                    String result1 = stmt1.getString(1);
                    if(result1.equals("online"))
                    {
                        res.setText("Users already logged in!");
                    }
                    else
                    {
                        res.setText("Logged in");
                        CallableStatement stmt2 = Main.db.prepareCall("{call proiect_sgbd.logged_in(?)}");
                        stmt2.setString(1,result);
                        stmt2.execute();
                        this.dispose();
                        meniu = new Meniu(result);
                    }
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getSource() == register) {
            res.setText("Going to register");
            this.dispose();
            registerForm = new RegisterForm();
        }
    }
}
