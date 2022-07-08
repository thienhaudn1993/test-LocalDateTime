package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProductAuntion();
    List<Product> getAllProductFinishedAuntion();
    List<Product> gettAllProductAuntionAndTypeProduct(String nameTypeProduct);
    List<Product> searchProductByNameByTypeProductByPrice(String nameProduct, String nameTypeProduct, Double min, Double max);
    List<Product> searchProductPricesOver250(String nameProduct, String nameTypeProduct, Double min);
}
