package kpi.labswebjava.config;

import kpi.labswebjava.service.mapper.CategoryMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersTestConfiguration {

    @Bean
    public CategoryMapper categoryMapper() {
        return Mappers.getMapper(CategoryMapper.class);
    }
}

