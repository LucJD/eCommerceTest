package com.eCommerce.demo.service;

import com.eCommerce.demo.model.Category;
import com.eCommerce.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepo;
    public void createCategory(Category category){
        categoryRepo.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Category readCategory(String categoryName){
        return categoryRepo.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Long id){
        return categoryRepo.findById(id);
    }

    public void updateCategory(Long id, Category newCat){
        Category category = categoryRepo.findById(id).get();
        category.setCategoryName(newCat.getCategoryName());
        category.setDescription(newCat.getDescription());
        category.setImageUrl(newCat.getImageUrl());
        categoryRepo.save(category);
    }

    public Optional<Category> getCategory(Long categoryId){
        return categoryRepo.findById(categoryId);
    }
}
