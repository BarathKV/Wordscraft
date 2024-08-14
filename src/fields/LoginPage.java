package fields;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import components.WFrame;
import connection.Receiver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends WFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(350, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateUser();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);
        panel.setBackground(new Color(255, 204, 153)); // RGB values for light orange

        Font customFont = new Font("Arial", Font.BOLD, 18);
        usernameField.setPreferredSize(new Dimension(20, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        usernameLabel.setFont(customFont);
        passwordLabel.setFont(customFont);
        loginButton.setFont(customFont);
        loginButton.setPreferredSize(new Dimension(100, 30));

        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void validateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(LoginPage.this, "Please enter both username and password.");
            return;
        }

        Receiver r = new Receiver();
        String user = r.authenticateUser(username, password);
        System.out.println(user); 

        if (user != null) {
            (new HomePage(user)).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(LoginPage.this, "Login failed");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage();
        });
    }
}
