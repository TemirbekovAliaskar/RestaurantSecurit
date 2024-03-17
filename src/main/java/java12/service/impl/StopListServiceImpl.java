package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.StopRequest;
import java12.dto.response.DefaultResponse;
import java12.dto.response.StopResponse;
import java12.entity.MenuItem;
import java12.entity.StopList;
import java12.repository.MenuItemRepository;
import java12.repository.StopListRepository;
import java12.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl implements StopService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;


    @Override
    public DefaultResponse save(Long menuId, StopRequest stopRequest) {
        MenuItem menu = menuItemRepository.getByIds(menuId);
        if (stopListRepository.existsByMenuItemAndDate(menu, stopRequest.date())) {
            return DefaultResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(" Для этого блюда уже сохранен стоп-лист на указанную дату!")
                    .build();
        }



        StopList stopList = new StopList();
        stopList.setReason(stopRequest.reason());
        stopList.setDate(stopRequest.date());
        stopList.setMenuItem(menu);
        stopListRepository.save(stopList);
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Удачно сохранен стоп-лист ! ")
                .build();
    }

    @Override @Transactional
    public DefaultResponse update(Long stopId, StopRequest stopRequest) {
        StopList stopList =  stopListRepository.getByIds(stopId);
        stopList.setReason(stopRequest.reason());
        stopList.setDate(stopRequest.date());
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Удачно изменен стоп-лист ! ")
                .build();
    }

    @Override
    public List<StopResponse> getALL() {
        return stopListRepository.getAll();
    }
    @Override
    public DefaultResponse deleteBy(Long stopId) {

        stopListRepository.delete(stopListRepository.getByIds(stopId));
        return  DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Удачно удален стоп-лист ! ")
                .build();
    }

    @Override
    public StopResponse findBy(Long stopId) {
        return stopListRepository.getStopId(stopId);
    }
}
