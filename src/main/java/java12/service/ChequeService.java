package java12.service;

import java12.dto.request.MenuItemCheckRequest;
import java12.dto.response.*;

import java.util.List;

public interface ChequeService {
    DefaultResponse save( MenuItemCheckRequest checkrequest);

    DefaultResponse update(Long cheId, MenuItemCheckRequest checkRequest);

    DefaultResponse delete(Long cheId);

    List<GetCheckResponse> getAll();

    GetCheckResponse findCheckById(Long checkId);

    SumCheckResponse sumWaiter();

    ResSumResponse getAverage(Long resId);
}
