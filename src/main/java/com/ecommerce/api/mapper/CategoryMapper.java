package com.ecommerce.api.mapper;

import com.ecommerce.api.domain.Category;
import com.ecommerce.api.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {
    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
