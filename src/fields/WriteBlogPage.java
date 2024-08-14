package fields;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.WFrame;
import connection.Sender;
import wmodel.Blog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WriteBlogPage extends WFrame {

    JTextField titleField = new JTextField(20);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton chooseImageButton = new JButton("Choose Image");
    String[] topics = { "Technology", "Science", "Photography", "Travel", "Food" };
    JComboBox<String> topicsDropdown = new JComboBox<>(topics);
    JTextArea contentArea = new JTextArea(10, 50);
    JScrollPane scrollPane = new JScrollPane(contentArea);
    JButton submitButton = new JButton("Submit");
    
    private byte[] selectedImageData;

    public WriteBlogPage(String userName) {
        System.out.println(userName); 
        setTitle("Write Blog");
        setLayout(new GridBagLayout());

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(titleField, gbc);

        // Image Chooser
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Image:"), gbc);

        gbc.gridx = 1;
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });
        add(chooseImageButton, gbc);

        // Topics Dropdown
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Topics:"), gbc);

        gbc.gridx = 1;
        add(topicsDropdown, gbc);

        // Blog Content
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Content:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveBlog(userName);
            }
        });
        add(submitButton, gbc);

        pack();
        setVisible(true);
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getAbsolutePath();
            try {
                selectedImageData = Files.readAllBytes(selectedFile.toPath());
                JOptionPane.showMessageDialog(this, "Selected File Path: " + selectedFilePath);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error reading image file: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Image selection canceled.");
        }
    }

    public byte[] getSelectedImageData() {
        return selectedImageData;
    }

    // private void SaveBlog(String userName) {
    //     String title = titleField.getText();
    //     String selectedTopic = (String) topicsDropdown.getSelectedItem();
    //     List<String> stopics = new ArrayList<String>();
    //     stopics.add(selectedTopic);
    //     String content = contentArea.getText();
    //     Sender a = new Sender();
    //     Blog result = a.ModelBlog(title, content, stopics, selectedImageData, userName);
    //     if (a.saveBlog(result)) {
    //         JOptionPane.showMessageDialog(null, "Blog saved successfully");
    //         dispose();
    //     } else {
    //         JOptionPane.showMessageDialog(null, "Error saving the blog");
    //     }
    // }
    
    private void SaveBlog(String userName) {
        // Validate title
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a title for the blog.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if validation fails
        }
    
        // Validate content
        String content = contentArea.getText().trim();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter content for the blog.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if validation fails
        }
    
        // Validate image
        if (selectedImageData == null) {
            JOptionPane.showMessageDialog(this, "Please choose an image for the blog.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if validation fails
        }
    
        // If all validations pass, proceed to save the blog
        if(topicsDropdown.getSelectedItem() == null) topicsDropdown.setSelectedItem("Technology");
        String selectedTopic = (String) topicsDropdown.getSelectedItem();
        List<String> stopics = new ArrayList<>();
        stopics.add(selectedTopic);
    
        Sender a = new Sender();
        System.out.println(userName+"from saveblog"); 
        Blog result = a.ModelBlog(title, content, stopics, selectedImageData, userName);
    
        if (a.saveBlog(result)) {
            JOptionPane.showMessageDialog(null, "Blog saved successfully");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Error saving the blog");
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WriteBlogPage("Suresh").setVisible(true);
            }
        });
    }
}
