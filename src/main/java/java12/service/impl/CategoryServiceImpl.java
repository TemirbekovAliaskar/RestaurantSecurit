package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.CategoryRequest;
import java12.dto.response.CategoryResponse;
import java12.dto.response.DefaultResponse;
import java12.entity.Category;
import java12.entity.MenuItem;
import java12.entity.Subcategory;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.exception.ForbiddenException;
import java12.exception.NotFoundException;
import java12.repository.CategoryRepository;
import java12.repository.MenuItemRepository;
import java12.repository.SubcategoryRepository;
import java12.repository.UserRepository;
import java12.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;
    @Override
    public DefaultResponse save(CategoryRequest categoryRequest) {
        getCurrentUser();
        Category category = new Category();
        category.setName(categoryRequest.name());
        categoryRepository.save(category);
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Удачно сохранен категория !!!")
                .build();
    }

    @Override
    public List<CategoryResponse> findAll() {
        try {
            getCurrentUser();
            List<Category> all = categoryRepository.findAll();
            List<CategoryResponse> categoryResponses = new ArrayList<>();
            for (Category category : all) {
                categoryResponses.add(new CategoryResponse(category.getId(), category.getName()));
            }
            return categoryResponses;
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public CategoryResponse findById(Long catId) {
        getCurrentUser();
        Category category = categoryRepository.getByIds(catId);
        CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName());
        return categoryResponse;
    }

    @Override @Transactional
    public DefaultResponse update(Long catId, CategoryRequest categoryRequest) {
        getCurrentUser();
        Category category = categoryRepository.getByIds(catId);
        category.setName(categoryRequest.name());
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Удачно изменен категории !!!")
                .build();
    }

    @Override
    @Transactional
    public DefaultResponse delete(Long catId) {
        getCurrentUser();
        Category category = categoryRepository.getByIds(catId);

        menuItemRepository.removeByCategoryId(category.getId());
        subcategoryRepository.removeByCategoryId(category.getId());

        categoryRepository.delete(category);
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Категория успешно удалена!!!")
                .build();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepository.getByEmail(email);
        if (current.getRole().equals(Role.ADMIN) || current.getRole().equals(Role.CHEF))
            return current;
        else throw new AccessDeniedException("Forbidden 403");
    }
}
