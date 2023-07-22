package com.eCommerce.demo.controller;

import com.eCommerce.demo.ApiResponse;
import com.eCommerce.demo.model.Category;
import com.eCommerce.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TODO add delete, put for updating category
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.findAllCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Object> getCategory(@PathVariable Long categoryId){
        Optional<Category> body = categoryService.getCategory(categoryId);
        if(Objects.nonNull(body)){
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "Category not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody Category category){
        //check if exists
        if(Objects.nonNull(categoryService.readCategory(categoryID))){

            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "category not found"), HttpStatus.NOT_FOUND);

    }
}
