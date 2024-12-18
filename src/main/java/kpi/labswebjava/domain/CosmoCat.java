package kpi.labswebjava.domain;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class CosmoCat {
    UUID cat_id;
    String cat_name;
    String email;
}
