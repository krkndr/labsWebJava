package kpi.labswebjava.domain;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class Product {
    UUID id;
    String name;
    String categoryID;
    String description;
    Double price;
}
