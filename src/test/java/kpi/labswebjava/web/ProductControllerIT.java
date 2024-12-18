package kpi.labswebjava.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kpi.labswebjava.domain.Product;
import kpi.labswebjava.dto.ProductDto;
import kpi.labswebjava.feature_toggle.FeatureToggleExtension;
import kpi.labswebjava.feature_toggle.FeatureToggles;
import kpi.labswebjava.feature_toggle.annotation.DisabledFeatureToggle;
import kpi.labswebjava.feature_toggle.annotation.EnabledFeatureToggle;
import kpi.labswebjava.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@ExtendWith(FeatureToggleExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller Tests")
public class ProductControllerIT {
    private static final UUID PRODUCT_ID = UUID.randomUUID();
    private ProductDto productDto;
    private Product mockProduct;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productDto = buildProductDto("Galaxy Milk", "Milk for astronauts", 29.7);
        mockProduct = Product.builder()
                .id(PRODUCT_ID)
                .name("Galaxy Milk")
                .description("Milk for astronauts")
                .categoryID(productDto.getCategoryID())
                .price(29.7)
                .build();
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    void testCreateProduct() throws Exception {
        when(productService.createProduct(any())).thenReturn(mockProduct);
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameProduct").value(productDto.getNameProduct()))
                .andExpect(jsonPath("$.description").value(productDto.getDescription()))
                .andExpect(jsonPath("$.price").value(productDto.getPrice()));
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    void testCreateProductWithNegativePrice() throws Exception {
        ProductDto invalidProductDto = buildProductDto("Star Milk", "Invalid price product", -5.0);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProductDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("Request validation failed"))
                .andExpect(jsonPath("$.invalidParams[0].fieldName").value("price"));
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    void testCreateProductWithInvalidName() throws Exception {
        ProductDto invalidNameProductDto = buildProductDto("Regular Milk", "Milk without cosmic term", 10.0);

        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidNameProductDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("Request validation failed"))
                .andExpect(jsonPath("$.invalidParams[0].fieldName").value("nameProduct"))
                .andExpect(jsonPath("$.invalidParams[0].reason").value("Product name must contain a cosmic term (e.g., 'star', 'galaxy', 'comet')"));
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.KITTY_PRODUCTS)
    void testDisabledCreateProduct() throws Exception {
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isNotFound());
    }

    private static ProductDto buildProductDto(String name, String description, double price) {
        return ProductDto.builder()
                .nameProduct(name)
                .categoryID(UUID.randomUUID().toString())
                .description(description)
                .price(price)
                .build();
    }
}