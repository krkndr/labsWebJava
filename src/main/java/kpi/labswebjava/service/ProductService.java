package kpi.labswebjava.service;

import kpi.labswebjava.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(UUID productId);
    Product updateProduct(UUID productId, Product product);
    List<Product> getAllProducts();
    void deleteProduct(UUID productId);
}