package java12.dto.response;

import lombok.Builder;

@Builder
public record AllRestaurants(Integer page,Integer size,Long id,String name, String location,String restType,int servicePercent,int numberOfEmployees) {
}
