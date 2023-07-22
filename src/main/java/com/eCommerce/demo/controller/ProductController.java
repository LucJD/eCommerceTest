package com.eCommerce.demo.controller;

import com.eCommerce.demo.ApiResponse;
import com.eCommerce.demo.dto.product.ProductDto;
import com.eCommerce.demo.model.Category;
import com.eCommerce.demo.service.CategoryService;
import com.eCommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been created"), HttpStatus.OK);
    }

}
