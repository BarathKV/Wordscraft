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

public class SearchByTitle extends WFrame {
    JLabel label = new JLabel("Enter Title:");
    JTextField textField = new JTextField();
    JButton button = new JButton("Search");
    public SearchByTitle(String currentUser) {
        setSize(280, 180);
        setTitle("Search By Title");
        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Create and add components to the panel
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResultView("Result of Search By Title",getResult(),currentUser);
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
        String Title = textField.getText();
        Receiver g = new Receiver();
        Blog[] resultByAuth = g.getBlogByTitle(Title);
        return resultByAuth;
    }
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new SearchByTitle("admin"));
        }
}