package dao.impl;

import dao.ArticleDao;
import entity.Article;
import entity.Category;
import entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    @SuppressWarnings("unused")
    private Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simpleapp", "root", "root");
            System.out.println("Got our connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public int createArticle(Article article) {
        Connection con = getConnection();
//        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement pr = con.prepareStatement("INSERT INTO article (" +
                    "title, body, category_id, users_id) VALUES (?, ?, ?, ?);");
            pr.setString(1, article.getTitle());
            pr.setString(2, article.getBody());
            pr.setInt(3, article.getCategory().getId());
            pr.setInt(4, 1);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return 0;
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int editArticle(Article article) {
        int result = 0;
        Connection con = getConnection();
        try {
            PreparedStatement pr = con.prepareStatement("UPDATE article SET title=?, body=?, category_id=?;");
            pr.setString(1, article.getTitle());
            pr.setString(2, article.getBody());
            pr.setInt(3, article.getCategory().getId());
            result = pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteArticle(int id) {
        Connection con = getConnection();
        try {
            PreparedStatement pr = con.prepareStatement("DELETE FROM article WHERE id=?;");
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Article getArticleById(int id) {
        Article article = null;
        Connection con = getConnection();
        try {
            PreparedStatement pr = con.prepareStatement("SELECT id, title, body, category_id, users_id FROM article WHERE id=?;");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                article = new Article();
                article.setId(rs.getInt(1));
                article.setTitle(rs.getString(2));
                article.setBody(rs.getString(3));
                Category category = new Category();
                category.setId(rs.getInt(4));
                article.setCategory(category);
                Users user = new Users();
                user.setId(rs.getInt(5));
                article.setUsers(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        Article article = null;
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT id, title, body, category_id, users_id FROM article;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                article = new Article();
                article.setId(rs.getInt(1));
                article.setTitle(rs.getString(2));
                article.setBody(rs.getString(3));
                Category category = new Category();
                category.setId(rs.getInt(4));
                article.setCategory(category);
                Users user = new Users();
                user.setId(rs.getInt(5));
                article.setUsers(user);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

//    public static void main(String[] args) {
//        ArticleDao art = new ArticleDaoImpl();
//        Article article = new Article();
//        Category category = new Category();
//        category.setId(1);
//        article.setBody("my body of the article");
//        article.setCategory(category);
//        article.setTitle("my title");
//        System.out.println(art.createArticle(article));
//    }
}
