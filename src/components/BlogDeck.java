package components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import wmodel.Blog;

public class BlogDeck extends JScrollPane{
    public BlogDeck(Blog[] blogset,String currentUser){
        JPanel deck = new JPanel();
        deck.setLayout(new GridLayout(blogset.length,1));
        for(Blog oneblog : blogset){
            deck.add(new BlogCard(oneblog,currentUser));
        }
        setViewportView(deck);
        setPreferredSize(new Dimension(380,280));
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
