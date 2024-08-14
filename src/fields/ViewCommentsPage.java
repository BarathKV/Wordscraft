package fields;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import components.CommentCard;
import components.WFrame;
import connection.Receiver;
import wmodel.Comment;

public class ViewCommentsPage extends WFrame{

    public ViewCommentsPage(String blog_title){
        setTitle("Comments");
        setSize(300, 220);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Receiver r = new Receiver();
        Comment[] commentset = r.getCommentByTitle(blog_title);
        JScrollPane comments_result = new JScrollPane();
        JPanel comment_deck = new JPanel();
        comment_deck.setLayout(new GridLayout(commentset.length,1));
        for(Comment onecomment : commentset){
            comment_deck.add(new CommentCard(onecomment));
            
        }
        comments_result.setViewportView(comment_deck);
        comments_result.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        comments_result.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(comments_result, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewCommentsPage("Data Time");
        });
    }
}
