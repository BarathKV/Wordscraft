// package components;

// import java.awt.BorderLayout;
// // import java.awt.Dimension;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.BorderFactory;
// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JPanel;

// import wmodel.Blog;
// import fields.ViewBlogPage;

// public class BlogCard extends JPanel{
//     public BlogCard(Blog blog,String currentUser){
//         JLabel title = new JLabel();
//         title.setText(blog.BlogTitle);
//         JLabel name = new JLabel();
//         name.setText(blog.User);
//         JLabel date = new JLabel();
//         date.setText((blog.BlogDate).toString());

//         title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
//         name.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
//         date.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

//         // Create a JPanel to hold the JLabels on the left side
//         JPanel leftPanel = new JPanel();
//         leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//         // leftPanel.setPreferredSize(new Dimension(200,40));
//         leftPanel.add(title);
//         leftPanel.add(name);
//         leftPanel.add(date);

//         // Create a JButton
//         JButton button = new JButton("Blog");
//         button.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 new ViewBlogPage(blog,currentUser);
//             }
//         });

//         JPanel rightpanel = new JPanel();
//         rightpanel.add(button, BorderLayout.CENTER);

//         setLayout(new GridLayout(1,2,5,5));
//     }
// }


package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import wmodel.Blog;
import fields.ViewBlogPage;

public class BlogCard extends JPanel {
    public BlogCard(Blog blog, String currentUser) {
        JLabel title = new JLabel();
        title.setText(blog.BlogTitle);
        JLabel name = new JLabel();
        name.setText(blog.User);
        String datevalue = blog.BlogDate.toString();
        datevalue = datevalue.substring(0, datevalue.length()-10);
        JLabel date = new JLabel(datevalue);

        title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        name.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        date.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // Create a JPanel to hold the JLabels on the left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(title);
        leftPanel.add(name);
        leftPanel.add(date);

        // Set the preferred size of leftPanel to 200x40
        leftPanel.setPreferredSize(new Dimension(200, 40));

        // Create a JButton
        JButton button = new JButton("Blog");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewBlogPage(blog, currentUser);
            }
        });

        JPanel rightPanel = new JPanel();
        rightPanel.add(button);

        setLayout(new BorderLayout());

        // Add leftPanel to the WEST (left) side and rightPanel to the CENTER
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}
