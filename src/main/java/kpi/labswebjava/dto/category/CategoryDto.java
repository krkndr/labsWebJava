package kpi.labswebjava.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CategoryDto {

    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    String nameCategory;
}
