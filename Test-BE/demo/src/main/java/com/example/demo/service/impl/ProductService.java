package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<Product> getAllProductAuntion() {
        return productRepository.findAllProductAuction();
    }

    @Override
    public List<Product> getAllProductFinishedAuntion() {
        return productRepository.findAllProductFinishedAuction();
    }

    @Override
    public List<Product> gettAllProductAuntionAndTypeProduct(String nameTypeProduct) {
        return productRepository.gettAllProductAuntionAndTypeProduct(nameTypeProduct);
    }

    @Override
    public List<Product> searchProductByNameByTypeProductByPrice(String nameProduct, String nameTypeProduct, Double min, Double max) {
        return productRepository.searchProductByNameByTypeProductByPrice(nameProduct, nameTypeProduct, min, max);
    }

    @Override
    public List<Product> searchProductPricesOver250(String nameProduct, String nameTypeProduct, Double min) {
        return productRepository.searchProductPricesOver250(nameProduct, nameTypeProduct, min);
    }


}
