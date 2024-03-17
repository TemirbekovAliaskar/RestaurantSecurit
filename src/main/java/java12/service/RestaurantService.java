package java12.service;

import java12.dto.request.RestaurantRequest;
import java12.dto.response.AllRestaurants;
import java12.dto.response.DefaultResponse;
import java12.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    DefaultResponse save(RestaurantRequest request);

    DefaultResponse update(Long resId, RestaurantRequest request);

    DefaultResponse delete(Long resId);

    List<AllRestaurants>allRestaurants(int page,int size);

    RestaurantResponse findById(Long id);
}
