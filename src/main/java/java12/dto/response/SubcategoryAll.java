package java12.dto.response;

import lombok.Builder;

@Builder
public record SubcategoryAll(
        Integer page,
        Integer size,
        Long id,String name

) {
}
