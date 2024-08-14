package components;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

public class WFrame extends JFrame{
    public WFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        Image WordsCraftIcon = Toolkit.getDefaultToolkit().getImage("D:/Projects/WordsCraft/WordscraftIcon.png");
        setIconImage(WordsCraftIcon);
    }
}
