package fields;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import components.ResultView;
import components.WFrame;
import connection.Receiver;
import wmodel.Blog;

public class SearchByAuthor extends WFrame {
    JLabel label = new JLabel("Enter UserName:");
    JTextField textField = new JTextField();
    JButton button = new JButton("Search");

    public SearchByAuthor(String currentUser) {
        setSize(280, 180);
        setTitle("Search By Author");
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Create and add components to the panel
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ResultView("Result of Search By Author",getResult(),currentUser);
                // new ViewBlogPage(resultByAuth[0]);
                // user = textField.getText();
            }

        });
        
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.setBorder(new EmptyBorder(15,15,15,15));
        getContentPane().add(panel,BorderLayout.CENTER);
        setVisible(true);
    }
    private Blog[] getResult() {
        String Author = textField.getText();
        Receiver g = new Receiver();
        Blog[] resultByAuth = g.getBlogByUser(Author);
        return resultByAuth;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchByAuthor("admin"));
    }
}