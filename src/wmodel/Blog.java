package wmodel;

import java.sql.Timestamp;
import java.util.List;

public class Blog {
    public int BlogID;
    public String BlogTitle;
    public String BlogContent;
    public List<String> BlogTopics;
    public byte[] BlogImage;
    public Timestamp BlogDate;
    public String User;
}
