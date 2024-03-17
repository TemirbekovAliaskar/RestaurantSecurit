package java12.dto.response;

import java12.entity.Subcategory;


public record MenuSearchResponse(Subcategory subcategory, String name, Long price, String description, boolean isVegetarian) {}
