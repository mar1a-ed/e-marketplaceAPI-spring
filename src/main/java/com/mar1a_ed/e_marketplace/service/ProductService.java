package com.mar1a_ed.e_marketplace.service;

import com.mar1a_ed.e_marketplace.exception.ProductNotFoundException;
import com.mar1a_ed.e_marketplace.model.entity.Product;
import com.mar1a_ed.e_marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product save(Product product){
        productRepository.save(product);
        return product;
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code){
        try{
            Product product = productRepository.findByCode(code);
            return product;
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Product {code=%s} not found", code));
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findByName(String name){
        try{
            List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
            return products;
        }catch (Exception e){
            throw new ProductNotFoundException(String.format("Product {name=%s} not found", name));
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        try{
            List<Product> products = productRepository.findAll();
            return products;
        }catch (Exception e){
            throw new ProductNotFoundException("No Registered products");
        }
    }

    @Transactional
    public void updateProductUnavailable(String code){
        Product product = findByCode(code);
        product.setStockQuantity(0);
        productRepository.save(product);
    }
}
