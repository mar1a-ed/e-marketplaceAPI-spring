package com.mar1a_ed.e_marketplace.dto.product;

import com.mar1a_ed.e_marketplace.model.entity.Product;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toProduct(ProductCreateDto productCreateDto){
        return new ModelMapper().map(productCreateDto, Product.class);
    }

    public static ProductResponseDto toDto(Product product){
        return new ModelMapper().map(product, ProductResponseDto.class);
    }

    public static List<ProductResponseDto> toListDto(List<Product> products){
        return products.stream().map(product -> toDto(product)).collect(Collectors.toList());
    }
}
