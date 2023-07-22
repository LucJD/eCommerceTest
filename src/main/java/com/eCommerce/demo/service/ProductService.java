package com.eCommerce.demo.service;

import com.eCommerce.demo.dto.product.ProductDto;
import com.eCommerce.demo.model.Category;
import com.eCommerce.demo.model.Product;
import com.eCommerce.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public Product getProductFromDto(ProductDto productDto, Category category){
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }
}
