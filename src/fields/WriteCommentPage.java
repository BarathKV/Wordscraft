package fields;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import components.WFrame;
import connection.Sender;
import wmodel.Comment;

public class WriteCommentPage extends WFrame {
    JLabel label = new JLabel("Comment:");
    JTextArea textField = new JTextArea(3, 20);
    JButton button = new JButton("Post");
    JScrollPane scrollPane = new JScrollPane(textField);
    
    // public WriteCommentPage(Blog comment_blog){
    public WriteCommentPage(String blog,String currentUser) {
        setSize(280, 180);
        setTitle("Write Comment");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add components to the frame with centered alignment
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.insets = new Insets(10, 0, 10, 0); // Add padding
        gbc.anchor = GridBagConstraints.CENTER;
        add(label, gbc);

        gbc.gridy = 1;
        // gbc.insets = new Insets(0, 0, 10, 0); // Add padding
        add(scrollPane, gbc);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate comment content
                String commentContent = textField.getText().trim();
                if (commentContent.isEmpty()) {
                    JOptionPane.showMessageDialog(WriteCommentPage.this, "Please enter a comment.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop execution if validation fails
                }

                // If validation passes, proceed to post the comment
                Sender s = new Sender();
                Comment result = s.ModelComment(currentUser, commentContent, blog);
                
                if (s.SaveComment(result)) {
                    JOptionPane.showMessageDialog(null, "Comment posted successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error posting the comment");
                }
            }
        });

        gbc.gridy = 4;
        add(button, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WriteCommentPage("admin","Data Time");
        });
    }
}
