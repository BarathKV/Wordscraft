package fields;

import javax.swing.*;

import components.BlogDeck;
import components.WFrame;
import connection.Receiver;
import wmodel.Blog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage extends WFrame {
    
    private class Home extends AbstractAction {
        private Home() {super("Home");}
        public void actionPerformed(ActionEvent arg0) {}}

    private class Write extends AbstractAction {
        private Write() {super("Write");}
        public void actionPerformed(ActionEvent arg0) {}}


    private class NarrowMenuItem extends JMenuItem {
        public NarrowMenuItem(Action a) {super(a);}
        public Dimension getMaximumSize() {
            return new Dimension(super.getPreferredSize().width, super.getMaximumSize().height);
        }
    }
    public HomePage(String currentUser){
        System.out.println(currentUser); 
        //menuBar
        setLayout(new BorderLayout());
        ActionListener towrite = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WriteBlogPage(currentUser);
            }
        };
        ActionListener searchByAuthor = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function here
                new SearchByAuthor(currentUser);
            }
        };
        ActionListener searchByTitle = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function here
                new SearchByTitle(currentUser);
            }
        };
        ActionListener searchByTopic = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your function here
                new SearchByTopic(currentUser);
            }
        };
        setTitle("WordsCraft");

        JMenuItem home = new NarrowMenuItem(new Home());
        JMenuItem write = new JMenuItem(new Write());
        write.addActionListener(towrite);
        // JMenuItem profile = new NarrowMenuItem(new Profile());
        JMenu search = new JMenu("Search");
        JMenuItem author = new JMenuItem("By Author");
        author.addActionListener(searchByAuthor);
        JMenuItem bytitle = new JMenuItem("By Title");
        bytitle.addActionListener(searchByTitle);
        JMenuItem topic = new JMenuItem("By Topic");
        topic.addActionListener(searchByTopic);
        JMenuBar main = new JMenuBar();
        search.add(author);
        search.add(bytitle);
        search.add(topic);
        main.add(home);
        main.add(write);
        main.add(search);
        // main.add(profile);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //displaying recent 5 blogs
        Receiver g = new Receiver();
        Blog[] recentblogs = g.getRecentBlogs();
        JScrollPane resultscroll = new BlogDeck(recentblogs, currentUser);

        add(resultscroll, BorderLayout.CENTER);
        setSize(400, 300);
        setResizable(true);
        add(main, BorderLayout.NORTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage("Deepak");
        });
    }
}
