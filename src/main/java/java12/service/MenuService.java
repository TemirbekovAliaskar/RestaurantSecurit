package java12.service;

import java12.dto.request.MenuRequest;
import java12.dto.response.DefaultResponse;
import java12.dto.response.MenuResponse;
import java12.dto.response.MenuSearchResponse;

import java.util.List;

public interface MenuService {
    DefaultResponse save(Long restId,Long subId ,MenuRequest menuRequest);

    DefaultResponse update(Long menuId, MenuRequest menuRequest);

     List<MenuResponse> getAll();

    MenuResponse findById(Long menuId);

    DefaultResponse delete(Long menuId);

    List<MenuSearchResponse> search(String word);

    List<MenuSearchResponse> sort(String word);

    List<MenuSearchResponse> filter(boolean words);
}
