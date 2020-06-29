package dao;

import entity.Article;

import java.util.List;

public interface ArticleDao {
    int createArticle(Article article);
    int editArticle(Article article);
    void deleteArticle(int id);
    Article getArticleById(int id);
    List<Article> getAllArticles();
}
