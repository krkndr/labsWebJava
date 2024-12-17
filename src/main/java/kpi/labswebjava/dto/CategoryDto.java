package kpi.labswebjava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kpi.labswebjava.dto.validation.CosmicWordCheck;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CategoryDto {
    @CosmicWordCheck
    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    String nameCategory;
}
