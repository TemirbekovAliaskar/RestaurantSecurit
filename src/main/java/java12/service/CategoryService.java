package java12.service;

import java12.dto.request.CategoryRequest;
import java12.dto.response.CategoryResponse;
import java12.dto.response.DefaultResponse;

import java.util.List;

public interface CategoryService {
    DefaultResponse save(CategoryRequest categoryRequest);

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long catId);

    DefaultResponse update(Long catId, CategoryRequest categoryRequest);

    DefaultResponse delete(Long catId);
}
