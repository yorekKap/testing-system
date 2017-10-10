package com.testing.system.web.container;

import com.testing.system.config.WebAppContext;
import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.entities.Category;
import com.testing.system.exceptions.web.BadRequestException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yuri on 11.10.17.
 */
public class CategoryContainer {
    private List<Category> categories;

    public CategoryContainer() {
        init();
    }

    public void init(){
        CategoryDao categoryDao = WebAppContext.get(CategoryDao.class);
        this.categories = new CopyOnWriteArrayList<>(categoryDao.findAll());
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void removeCategory(Long categoryId){
        this.categories.removeIf(c -> c.getId().equals(categoryId));
    }

    public void updateCategory(Long id, String categoryTitle){
        this.categories.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new BadRequestException("No category with id " + id + " in container"));
    }

    public List<Category> getCategories(){
        return categories;
    }
}
