package components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import wmodel.Comment;

public class CommentCard extends JPanel{
    public CommentCard(Comment comment){
        JLabel user = new JLabel(comment.CommentUser);
        JTextArea content = new JTextArea(comment.CommentContent);
        JScrollPane contentPane = new JScrollPane(content);
        content.setEditable(false);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        JLabel date = new JLabel();
        date.setText((comment.CommentDate).toString());

        user.setBorder(new EmptyBorder(0, 20, 0, 0));
        contentPane.setBorder(new EmptyBorder(0, 20, 0, 0));
        date.setBorder(new EmptyBorder(0, 20, 0, 0));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add components to the frame with centered alignment
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.insets = new Insets(10, 0, 10, 0); // Add padding
        add(user, gbc);

        gbc.gridy = 1;
        add(date,gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        // gbc.insets = new Insets(0, 0, 10, 0); // Add padding
        add(contentPane, gbc);

    }
}
