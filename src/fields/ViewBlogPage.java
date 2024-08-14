package fields;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import components.WFrame;
import connection.Receiver;
import wmodel.Blog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ViewBlogPage extends WFrame {

    public ViewBlogPage(Blog blog, String currentUser) {
        setTitle(blog.BlogTitle);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel(blog.BlogTitle);
        add(titleLabel, gbc);

        // Author
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Author:"), gbc);

        gbc.gridx = 1;
        JLabel authorLabel = new JLabel(blog.User);
        add(authorLabel, gbc);

        // Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel dateLabel = new JLabel("Date:");
        add(dateLabel, gbc);

        gbc.gridx = 1;
        String datevalue = blog.BlogDate.toString();
        datevalue = datevalue.substring(0, datevalue.length()-5);
        JLabel dateValuLabel = new JLabel(datevalue);
        add(dateValuLabel, gbc);

        // Image
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel imageLabel = new JLabel();
        imageLabel.setSize(new Dimension(100, 300));
        try {
            Image image = ImageIO.read(new ByteArrayInputStream(blog.BlogImage));
            if (image != null) {
                ImageIcon icon = new ImageIcon(image);
                Image scaledImage = icon.getImage().getScaledInstance(280, 144, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                imageLabel.setText("No Image");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            imageLabel.setText("No Image");
        }
        add(imageLabel, gbc);

        // Blog Content
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Content:"), gbc);

        gbc.gridy = 5;
        JTextArea contentArea = new JTextArea(15, 50);
        contentArea.append(blog.BlogContent);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setPreferredSize(new Dimension(400,240));
        add(scrollPane, gbc);

        gbc.gridy = 6;
        gbc.gridheight =1;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(1, 2, 0, 15));
        JButton comments = new JButton("Comments");
        buttonpanel.add(comments);
        comments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCommentsPage(blog.BlogTitle);
            }
        });
        JButton addcomments = new JButton("Add Comments");
        buttonpanel.add(addcomments);
        addcomments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WriteCommentPage(blog.BlogTitle, currentUser);
            }
        });
        add(buttonpanel, gbc);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Receiver g = new Receiver();
        Blog[] recentblogs = g.getRecentBlogs();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewBlogPage(recentblogs[0], "Deepak").setVisible(true);
            }
        });
    }
}
