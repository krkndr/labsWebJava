package kpi.labswebjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import kpi.labswebjava.dto.validation.CosmicWordCheck;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductDto {
    @CosmicWordCheck
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @NotBlank(message = "Product name cannot be blank")
    String nameProduct;

    String categoryID;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description;

    @PositiveOrZero(message = "Price must be positive")
    Double price;
}
