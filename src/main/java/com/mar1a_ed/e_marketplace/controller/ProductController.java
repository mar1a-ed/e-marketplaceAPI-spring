package com.mar1a_ed.e_marketplace.controller;

import com.mar1a_ed.e_marketplace.dto.product.ProductCreateDto;
import com.mar1a_ed.e_marketplace.dto.product.ProductMapper;
import com.mar1a_ed.e_marketplace.dto.product.ProductResponseDto;
import com.mar1a_ed.e_marketplace.model.entity.Product;
import com.mar1a_ed.e_marketplace.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> register(@Valid @RequestBody ProductCreateDto productCreateDto){
        Product product = ProductMapper.toProduct(productCreateDto);
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(product));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductResponseDto> findByCode(@Valid @PathVariable String code){
        Product product = productService.findByCode(code);
        ProductResponseDto productResponseDto = ProductMapper.toDto(product);
        return ResponseEntity.ok().body(productResponseDto);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<ProductResponseDto>> findByName(@Valid @PathVariable String name){
        List<Product> products = productService.findByName(name);
        return ResponseEntity.ok(ProductMapper.toListDto(products));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> findAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(ProductMapper.toListDto(products));
    }

    @PatchMapping("/stock/unavailable/{code}")
    public ResponseEntity<Void> updateProductUnavailable(@Valid @PathVariable String code){
        productService.updateProductUnavailable(code);
        return ResponseEntity.noContent().build();
    }
}
