package kpi.labswebjava.dto.CosmoCat;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
public class CosmoCatEntryDto {
    UUID cat_id;
    String cat_name;
    String email;
}