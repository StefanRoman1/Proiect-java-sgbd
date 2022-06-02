package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

/**
 * Formularul de logare.
 */

public class LoginForm extends JFrame implements ActionListener{

    private Container c;
    private JLabel name;
    private JTextField tname;
    private JLabel password;
    private JTextField tpassword;
    private JButton login;
    private JButton register;
    private JLabel res;
    public RegisterForm registerForm;
    public Meniu meniu;

    /**
     * In constructor creeam interfata grafica a formularului de logare
     */
    public LoginForm() {
        setTitle("Login Form");
        setBounds(600, 100, 400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        name = new JLabel("Nickname");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setForeground(Color.getHSBColor(12.7f,50f,40f));
        name.setSize(100, 20);
        name.setLocation(150, 100);
        c.add(name);

        tname = new JTextField();
        tname.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 30);
        tname.setLocation(100, 130);
        tname.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tname);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setForeground(Color.getHSBColor(12.7f,50f,40f));
        password.setSize(100, 20);
        password.setLocation(150, 200);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(190, 30);
        tpassword.setLocation(100, 230);
        tpassword.setBackground(Color.getHSBColor(12.7f,50f,40f));
        tpassword.setForeground(Color.getHSBColor(15.5f,55f,60f));
        c.add(tpassword);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setBackground(Color.getHSBColor(12.7f,50f,40f));
        login.setForeground(Color.getHSBColor(15.5f,55f,60f));
        login.setSize(100, 20);
        login.setLocation(150, 330);
        login.addActionListener(this);
        c.add(login);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setBackground(Color.getHSBColor(12.7f,50f,40f));
        register.setForeground(Color.getHSBColor(15.5f,55f,60f));
        register.setSize(100, 20);
        register.setLocation(150, 380);
        register.addActionListener(this);
        c.add(register);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setForeground(Color.getHSBColor(12.7f,50f,40f));
        res.setSize(500, 25);
        res.setLocation(50, 450);
        c.add(res);

        setVisible(true);
        c.setBackground(Color.getHSBColor(15.5f,55f,60f));
    }
    /**
     * Daca este apasat butonul de login, verificam ca utilizatorul sa existe, in caz afirmativ verificam sa nu fie
     * conectat deja.
     * Daca este apasat butonul de register, deschidem formularul de inregistrare.
     * @param e
     */
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
