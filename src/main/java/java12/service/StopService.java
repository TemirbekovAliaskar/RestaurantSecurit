package java12.service;

import java12.dto.request.StopRequest;
import java12.dto.response.DefaultResponse;
import java12.dto.response.StopResponse;

import java.util.List;

public interface StopService {
    DefaultResponse save(Long menuId, StopRequest stopRequest);

    DefaultResponse update(Long stopId, StopRequest stopRequest);

   List<StopResponse> getALL();

    DefaultResponse deleteBy(Long stopId);

    StopResponse findBy(Long stopId);
}
