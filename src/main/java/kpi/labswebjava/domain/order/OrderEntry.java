package kpi.labswebjava.domain.order;

import kpi.labswebjava.domain.Product;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class OrderEntry {
    UUID productID;
    int quantity;
}
