package fields;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.WFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authentication extends WFrame {
    public JPanel Chooser;

    public Authentication() {
        setTitle("WordsCraft");
        setSize(250, 80);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Chooser = new JPanel();

        JButton Login = new JButton("Login");
        Chooser.add(Login);

        JButton SignUp = new JButton("SignUp");
        Chooser.add(SignUp);

        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
        });

        SignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignUpPage();
                dispose();
            }
        });

        add(Chooser);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Authentication();
    }
}