package fields;

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

public class SearchByTopic extends WFrame {
    JLabel label = new JLabel("Enter Topic:");
    JTextField textField = new JTextField();
    JButton button = new JButton("Search");
    public SearchByTopic(String curretUser) {
        setSize(280, 180);
        setTitle("Search By Topic");
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Create and add components to the panel
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResultView("Result of Search By Topic",getResult(),curretUser);
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(panel);
        setVisible(true);
    }
    private Blog[] getResult() {
        String Topic = textField.getText();
        Receiver g = new Receiver();
        Blog[] resultByAuth = g.getBlogByTopic(Topic);
        return resultByAuth;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchByTopic("admin"));
    }
}