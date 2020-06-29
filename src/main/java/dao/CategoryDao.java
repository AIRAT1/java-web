package dao;

import entity.Category;

import java.util.List;

public interface CategoryDao {
    int createCategory(Category category);
    void deleteCategory(Category category);
    int editCategory(Category category);
    List<Category> getAllCategories();
}
