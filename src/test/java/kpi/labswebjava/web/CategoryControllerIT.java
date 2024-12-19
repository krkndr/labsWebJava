package kpi.labswebjava.web;

import kpi.labswebjava.dto.category.CategoryDto;
import kpi.labswebjava.repository.CategoryRepository;
import kpi.labswebjava.repository.entity.CategoryEntity;
import kpi.labswebjava.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@AutoConfigureMockMvc
@DisplayName("Category Controller")
@SpringBootTest
public class CategoryControllerIT extends Abstract {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        Mockito.reset(categoryService);
        categoryRepository.deleteAll();
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        saveCategoryEntity();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldGetCategoryById() throws Exception {
        CategoryEntity category = saveCategoryEntity();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/" + category.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldGetNotFoundCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldCreateCategory() throws Exception {
        CategoryDto categoryDto = createCategoryDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(categoryDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldCreateCategoryFailed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/category/{id}", categoryEntity.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void shouldDeleteCategoryNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/category/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private CategoryDto createCategoryDto() {
        return CategoryDto.builder()
                .nameCategory("Cosmo Drink update")
                .build();
    }

    private CategoryEntity saveCategoryEntity() {
        return categoryRepository.save(CategoryEntity.builder()
                .nameCategory("Cosmo Drink")
                .build());
    }
}
