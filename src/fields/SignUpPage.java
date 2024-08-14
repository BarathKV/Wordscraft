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
import connection.Sender;
import wmodel.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends WFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public SignUpPage() {
        setTitle("Sign Up");
        setSize(290, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveUserToDatabase();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(signUpButton);
        panel.setBackground(new Color(255, 204, 153));
        Font CustomFont = new Font("Arial", Font.BOLD, 18);

        usernameLabel.setFont(CustomFont);
        passwordLabel.setFont(CustomFont);
        signUpButton.setFont(CustomFont);

        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void saveUserToDatabase() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
    
        // Perform form validation
        if (username.isEmpty() || passwordChars.length == 0) {
            JOptionPane.showMessageDialog(SignUpPage.this, "Please enter both username and password.");
            return;
        }
    
        Sender a = new Sender();
        User user = a.ModelUser(username, password);
    
        // Check if the user is valid before saving
        if (user!=null) {
            if (a.SaveUser(user)) {
                JOptionPane.showMessageDialog(SignUpPage.this, "Sign Up Successful");
                new LoginPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(SignUpPage.this, "Sign Up failed");
            }
        } else {
            JOptionPane.showMessageDialog(SignUpPage.this, "Invalid username or password format.");
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignUpPage();
        });
    }
}
