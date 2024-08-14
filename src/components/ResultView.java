package components;

import javax.swing.*;

import connection.Receiver;

import java.awt.*;

import wmodel.Blog;

public class ResultView extends WFrame {
    public ResultView(String FrameTitle ,Blog[] blogset,String currentUser) {
        setTitle(FrameTitle);
        JScrollPane resultscroll = new BlogDeck(blogset,currentUser);
        add(resultscroll, BorderLayout.CENTER);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        Receiver g =new Receiver();
        SwingUtilities.invokeLater(() -> {
            new ResultView("Search By Title",g.getBlogByTitle("Title"),"admin");
        });
    }
}