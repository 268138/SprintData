package com.example.SprintData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final int PAGE_SIZE = 5; // 5 products per page

    public List<Product> getProducts(int page) {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(page, PAGE_SIZE));
        return productsPage.getContent();
    }
}
