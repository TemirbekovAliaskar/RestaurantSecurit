package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.Subcategoryrequest;
import java12.dto.response.*;
import java12.entity.Category;
import java12.entity.Subcategory;
import java12.entity.User;
import java12.repository.CategoryRepository;
import java12.repository.MenuItemRepository;
import java12.repository.SubcategoryRepository;
import java12.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public DefaultResponse save(Long catId, Subcategoryrequest subcategoryrequest) {
        Category category = categoryRepository.getByIds(catId);
        Subcategory subcategory = new Subcategory();
        subcategory.setName(subcategoryrequest.name());
        subcategory.setCategory(category);
        subcategoryRepository.save(subcategory);
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Успешно сохранен подкатегории !")
                .build();
    }
    @Override
    public List<SubcategoryAll> findAll(Integer page,Integer size,Long catId) {


        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Subcategory> subcategoryPage = subcategoryRepository.getAllPage(pageable,catId);

        List<SubcategoryAll> responses = new ArrayList<>();
//        List<Subcategory> subcategories = subcategoryRepository.findAllId(catId);
//        List<Subcategoryresponse> subcategoryresponses = new ArrayList<>();
//        for (Subcategory subcategory : subcategories) {
//            subcategoryresponses.add( new Subcategoryresponse(subcategory.getId(),subcategory.getName()));
//        }
//        return subcategoryresponses;

        for (Subcategory subcategory : subcategoryPage.getContent()) {
            SubcategoryAll subcategoryAll = SubcategoryAll.builder()
                    .page(subcategoryPage.getNumber()+1)
                    .size(subcategoryPage.getTotalPages())
                    .id(subcategory.getId())
                    .name(subcategory.getName())
                    .build();

            responses.add(subcategoryAll);
        }
        return responses;

    }


    @Override @Transactional
    public DefaultResponse update(Long subId, Subcategoryrequest subcategoryrequest) {
        Subcategory subcategory = subcategoryRepository.getByIds(subId);
        subcategory.setName(subcategoryrequest.name());
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Успешно изменен подкатегории !")
                .build();
    }

    @Override
    public Subcategoryresponse findById(Long subId) {
        Subcategory subcategory = subcategoryRepository.getByIds(subId);
        Subcategoryresponse subcategoryresponse = new Subcategoryresponse(subcategory.getId(),subcategory.getName());
        return subcategoryresponse;
    }

    @Override @Transactional
    public DefaultResponse delete(Long subId) {
        Subcategory sub = subcategoryRepository.getByIds(subId);
        menuItemRepository.removeBySubCategoryId(subId);
        subcategoryRepository.delete(sub);
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Успешно удален подкатегории !")
                .build();
    }

    @Override
    public List<SubcategoryCategoryResponse> getAllCategory() {
        return subcategoryRepository.getAllCateg();
    }
}
