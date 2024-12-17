package kpi.labswebjava.service.mapper;


import kpi.labswebjava.domain.Product;
import kpi.labswebjava.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  ProductMapper {
    @Mapping(source = "name", target = "nameProduct")
    @Mapping(source = "categoryID", target = "categoryID")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    ProductDto toProductDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryID", target = "categoryID")
    @Mapping(source = "nameProduct", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    Product toProduct(ProductDto productDto);
}
