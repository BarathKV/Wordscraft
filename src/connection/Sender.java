package connection;

import java.sql.*;
import java.util.List;

import wmodel.Blog;
import wmodel.Comment;
import wmodel.User;

public class Sender {

    public void finalize(){}

    private Connection getConnection() throws SQLException {
        // Replace these details with your database connection information
        String url = "jdbc:mysql://localhost:3306/wordscraft";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public Blog ModelBlog(String blogTitle, String blogContent, List<String> blogTopics,byte[] blogImage, String user) {
        Blog blog = new Blog();
        blog.BlogTitle = blogTitle;
        blog.BlogContent = blogContent;
        blog.BlogTopics = blogTopics;
        blog.BlogImage = blogImage;
        blog.User = user;
        return blog;
    }

    public boolean saveBlog(Blog blog) {
        try (Connection connection = getConnection();
            
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO wordscraft.blogs (blog_title, blog_content, blog_topics, blog_image, blog_date, user) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, blog.BlogTitle);
            statement.setString(2, blog.BlogContent);
            statement.setString(3, String.join(",", blog.BlogTopics));
            statement.setBytes(4, blog.BlogImage);
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.setString(6, blog.User);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User ModelUser(String UserName,String Password){
        User user = new User();
        user.UserName = UserName;
        user.Password = Password;
        return user;
    }

    public boolean SaveUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO wordscraft.users (username,password) VALUES (?,?)")) {
            statement.setString(1, user.UserName);
            statement.setString(2, user.Password);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Comment ModelComment(String user,String content,String blog){
        Comment comment = new Comment();
        comment.CommentUser = user;
        comment.CommentContent = content;
        comment.CommentBlog = blog;
        return comment;
    }

    public boolean SaveComment(Comment comment){
        try (Connection connection = getConnection();
            
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO wordscraft.comments (comment_user, comment_blog, comment_content,comment_date) " +
                             "VALUES (?, ?, ?, ?)")) {
            statement.setString(1, comment.CommentUser);
            statement.setString(2, comment.CommentBlog);
            statement.setString(3, comment.CommentContent);
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
