package kpi.labswebjava.service.mapper;

import kpi.labswebjava.domain.Category;
import kpi.labswebjava.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "name", target = "nameCategory")
    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nameCategory", target = "name")
    Category toCategory(CategoryDto categoryDto);
}
