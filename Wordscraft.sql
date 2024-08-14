
CREATE DATABASE IF NOT EXISTS wordscraft;

CREATE TABLE IF NOT EXISTS wordscraft.blogs (
    blog_id INT AUTO_INCREMENT PRIMARY KEY,
    blog_title VARCHAR(50) UNIQUE,
    blog_content VARCHAR(3000),
    blog_topics VARCHAR(200),
    blog_image MEDIUMBLOB,
    blog_date DATETIME,
    user VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS wordscraft.users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS wordscraft.comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    comment_user VARCHAR(50),
    comment_blog VARCHAR(50),
    comment_content VARCHAR(500),
    comment_date DATETIME
);