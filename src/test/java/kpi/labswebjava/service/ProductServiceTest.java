package kpi.labswebjava.service;

import kpi.labswebjava.domain.Product;
import kpi.labswebjava.service.exception.ProductNotFoundException;
import kpi.labswebjava.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Product Service Tests")
@SpringBootTest(classes = ProductServiceImpl.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(UUID.randomUUID())
                .categoryID(UUID.randomUUID().toString())
                .name("Cosmic Flavored Milk")
                .description("Premium milk for space kittens")
                .price(19.99)
                .build();
    }

    @Test
    @Order(1)
    void testCreateProduct() {
        Product newProduct = productService.createProduct(product);

        assertThat(newProduct).isNotNull();
        assertThat(newProduct.getId()).isNotNull();
        assertThat(productService.getAllProducts()).contains(newProduct);
    }

    @Test
    @Order(2)
    void testUpdateProduct() {
        productService.createProduct(product);
        Product updatedProduct = Product.builder()
                .id(product.getId())
                .categoryID(product.getCategoryID())
                .name("Updated Cosmic Flavored Milk")
                .description("Updated premium milk description")
                .price(21.99)
                .build();

        Product result = productService.updateProduct(product.getId(), updatedProduct);

        assertThat(result).isNotNull();
        assertEquals("Updated Cosmic Flavored Milk", result.getName());
        assertEquals("Updated premium milk description", result.getDescription());
        assertEquals(21.99, result.getPrice());
    }

    @Test
    @Order(3)
    void testUpdateProductNotFound() {
        assertThrows(ProductNotFoundException.class, () ->
                productService.updateProduct(UUID.randomUUID(), product));
    }

    @Test
    @Order(4)
    void testGetProductById() {
        productService.createProduct(product);
        Product result = productService.getProductById(product.getId());

        assertThat(result).isNotNull();
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    @Order(5)
    void testGetProductByIdNotFound() {
        assertThrows(ProductNotFoundException.class, () ->
                productService.getProductById(UUID.randomUUID()));
    }

    @Test
    void testDeleteProduct() {
        productService.createProduct(product);
        productService.deleteProduct(product.getId());
        assertThat(productService.getAllProducts()).doesNotContain(product);
    }

    @Test
    void testDeleteProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertDoesNotThrow(() -> productService.deleteProduct(nonExistentId));
    }

    @ParameterizedTest
    @MethodSource("getProducts")
    @Order(8)
    void testGetAllProducts(Product product) {
        productService.createProduct(product);
        List<Product> result = productService.getAllProducts();
        assertThat(result).contains(product);
    }

    private static Stream<Product> getProducts() {
        return Stream.of(
                buildProduct(UUID.randomUUID().toString(), "Nebula Milk", "Milk enriched with cosmic vitamins", 25.0),
                buildProduct(UUID.randomUUID().toString(), "Galaxy Energy Drink", "Boost your stamina in space", 5.5),
                buildProduct(UUID.randomUUID().toString(), "Comet Chips", "Crispy chips from comets", 3.0)
        );
    }

    private static Product buildProduct(String categoryId, String name, String description, double price) {
        return Product.builder()
                .id(UUID.randomUUID())
                .categoryID(categoryId)
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}

