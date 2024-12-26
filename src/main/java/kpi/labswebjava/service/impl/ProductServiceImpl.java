package kpi.labswebjava.service.impl;

import kpi.labswebjava.domain.Product;
import kpi.labswebjava.service.ProductService;
import kpi.labswebjava.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final List<Product> products = new ArrayList<>(buildAllProductMock());

    @Override
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product getProductById(UUID productId) {
        return Optional.of(products.stream()
                        .filter(details -> details.getId().equals(productId)).findFirst())
                .get()
                .orElseThrow(() -> {
                    log.info("Product with id {} not found in mock", productId);
                    return new ProductNotFoundException(productId);
                });
    }

    @Override
    public Product updateProduct(UUID productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        Product updatedExistingProduct = Product.builder()
                .id(productId)
                .categoryID(updatedProduct.getCategoryID())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .build();

        log.info("Product with id {} updated successfully", productId);
        products.set(products.indexOf(existingProduct), updatedExistingProduct);
        return updatedExistingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void deleteProduct(UUID productId) {
        Optional<Product> productOptional = products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();

        if (productOptional.isPresent()) {
            products.remove(productOptional.get());
            log.info("Product with id {} deleted successfully", productId);
        } else {
            log.info("Product with id {} not found, no action taken", productId);
        }
    }

    private List<Product> buildAllProductMock() {
        return List.of(
                Product.builder()
                        .id(UUID.randomUUID())
                        .categoryID(UUID.randomUUID().toString())
                        .name("Comet Streak Pen")
                        .description("A pen with a cosmic touch, tracing your thoughts like a comet streak.")
                        .price(7.99)
                        .build(),

                Product.builder()
                        .id(UUID.randomUUID())
                        .categoryID(UUID.randomUUID().toString())
                        .name("Asteroid Rock Candy")
                        .description("Delicious treats made from asteroid-like rock candy.")
                        .price(12.99)
                        .build(),

                Product.builder()
                        .id(UUID.randomUUID())
                        .categoryID(UUID.randomUUID().toString())
                        .name("Satellite Earphones")
                        .description("Listen to music like you’re orbiting the cosmos with these satellite-inspired earphones.")
                        .price(29.99)
                        .build(),

                Product.builder()
                        .id(UUID.randomUUID())
                        .categoryID(UUID.randomUUID().toString())
                        .name("Meteor Bath Bomb")
                        .description("Experience a meteor shower in your bath.")
                        .price(8.99)
                        .build(),

                Product.builder()
                        .id(UUID.randomUUID())
                        .categoryID(UUID.randomUUID().toString())
                        .name("Dark Matter Coffee")
                        .description("Bold and intense coffee brewed with the mystery of dark matter.")
                        .price(15.99)
                        .build()

        );
    }
}
