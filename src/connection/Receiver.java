package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wmodel.Blog;
import wmodel.Comment;

public class Receiver {

    public void finalize(){}

    private Connection getConnection() throws SQLException {
        // Replace these details with your database connection information
        String url = "jdbc:mysql://localhost:3306/wordscraft";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public Blog[] getBlogByTitle(String title) {
        List<Blog> blogs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM wordscraft.blogs WHERE blog_title LIKE ? ORDER BY blog_date DESC")) {
            statement.setString(1, "%" + title + "%"); // Use LIKE for partial matches

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.BlogID = resultSet.getInt("blog_id");
                    blog.BlogTitle = resultSet.getString("blog_title");
                    blog.BlogContent = resultSet.getString("blog_content");
                    blog.BlogTopics = parseTopics(resultSet.getString("blog_topics"));
                    blog.BlogImage = resultSet.getBytes("blog_image");
                    blog.BlogDate = resultSet.getTimestamp("blog_date");
                    blog.User = resultSet.getString("user");

                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs.toArray(new Blog[0]);
    }

    private List<String> parseTopics(String topics) {
        // Helper method to convert the comma-separated topics to a list
        return new ArrayList<>(List.of(topics.split(",")));
    }

    public Blog[] getRecentBlogs() {
        List<Blog> blogs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM wordscraft.blogs ORDER BY blog_date DESC LIMIT 5")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.BlogID = resultSet.getInt("blog_id");
                    blog.BlogTitle = resultSet.getString("blog_title");
                    blog.BlogContent = resultSet.getString("blog_content");
                    blog.BlogTopics = parseTopics(resultSet.getString("blog_topics"));
                    blog.BlogImage = resultSet.getBytes("blog_image");
                    blog.BlogDate = resultSet.getTimestamp("blog_date");
                    blog.User = resultSet.getString("user");

                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs.toArray(new Blog[0]);
    }

    public Blog[] getBlogByUser(String username) {
        List<Blog> blogs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM wordscraft.blogs WHERE user = ? ORDER BY blog_date DESC")) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.BlogID = resultSet.getInt("blog_id");
                    blog.BlogTitle = resultSet.getString("blog_title");
                    blog.BlogContent = resultSet.getString("blog_content");
                    blog.BlogTopics = parseTopics(resultSet.getString("blog_topics"));
                    blog.BlogImage = resultSet.getBytes("blog_image");
                    blog.BlogDate = resultSet.getTimestamp("blog_date");
                    blog.User = resultSet.getString("user");

                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs.toArray(new Blog[0]);
    }

    public Blog[] getBlogByTopic(String topic) {
        List<Blog> blogs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM wordscraft.blogs WHERE blog_topics LIKE ? ORDER BY blog_date DESC")) {
            statement.setString(1, "%" + topic + "%"); // Use LIKE for partial matches

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Blog blog = new Blog();
                    blog.BlogID = resultSet.getInt("blog_id");
                    blog.BlogTitle = resultSet.getString("blog_title");
                    blog.BlogContent = resultSet.getString("blog_content");
                    blog.BlogTopics = parseTopics(resultSet.getString("blog_topics"));
                    blog.BlogImage = resultSet.getBytes("blog_image");
                    blog.BlogDate = resultSet.getTimestamp("blog_date");
                    blog.User = resultSet.getString("user");

                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs.toArray(new Blog[0]);
    }

    public String authenticateUser(String username,String password) {
        try {
            Connection connection = getConnection();
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User is authenticated
                connection.close();
                return username;
            } else {
                // User is not authenticated
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comment[] getCommentByTitle(String Title){
        List<Comment> comments = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM wordscraft.comments WHERE comment_blog LIKE ? ORDER BY comment_date DESC")) {
            statement.setString(1, "%" + Title + "%"); // Use LIKE for partial matches

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.CommentUser = resultSet.getString("comment_user");
                    comment.CommentBlog = resultSet.getString("comment_blog");
                    comment.CommentContent = resultSet.getString("comment_content");
                    comment.CommentDate = resultSet.getTimestamp("comment_date");

                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments.toArray(new Comment[0]);
    }
    
}
