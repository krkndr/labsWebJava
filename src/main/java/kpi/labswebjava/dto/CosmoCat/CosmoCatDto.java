package kpi.labswebjava.dto.CosmoCat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.util.UUID;

@Value
@Builder
@Jacksonized
public class CosmoCatDto {

    @NotNull(message = "ID is required")
    UUID cat_id;

    @Size(max = 100, message = "The name must not exceed 100 characters")
    @NotBlank(message = "The name cannot be empty or null")
    String cat_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    String email;
}
