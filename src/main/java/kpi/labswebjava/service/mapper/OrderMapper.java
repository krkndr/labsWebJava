package kpi.labswebjava.service.mapper;

import kpi.labswebjava.domain.order.OrderEntry;
import kpi.labswebjava.dto.order.OrderEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "productID", target = "productID")
    @Mapping(source = "quantity", target = "quantity")
    OrderEntry toOrderItem(OrderEntryDto orderItemDto);
}
