package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.MenuItemCheckRequest;
import java12.dto.response.*;
import java12.entity.*;
import java12.entity.enums.Role;
import java12.exception.FilledException;
import java12.exception.ForbiddenException;
import java12.exception.NotFoundException;
import java12.repository.*;
import java12.service.ChequeService;
import java12.service.MenuService;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final StopListRepository stopListRepository;



    @Override
    @Transactional
    public DefaultResponse save(MenuItemCheckRequest menuItemCheckRequest) {

        User currentUser = getCurrentUser();
        List<Long> menuIDs = menuItemCheckRequest.menuItemIds();

        menuIDs.addAll(menuItemCheckRequest.menuItemIds());
        List<MenuItem> allMenuItems = menuItemRepository.getAllMenuId(menuIDs);

        for (Long menuItemId : menuIDs) {
            boolean exists = allMenuItems.stream().anyMatch(menuItem -> menuItem.getId().equals(menuItemId));
            if (!exists) {
                throw new NotFoundException(" Такой id  " + menuItemId + " не найдена в базе данных!");
            }
        }
        for (MenuItem menuItem : allMenuItems) {
                StopList stoplist = stopListRepository.getByMenuItemId(menuItem.getId());
                if (stoplist != null && stoplist.getDate().isAfter(LocalDateTime.now().minusMinutes(1))) {
                    throw new FilledException("Позиция " + menuItem.getName() + " уже в стоп-листе более  часов!");
                }
        }
        int total = 0;
        for (MenuItem menuItem : allMenuItems) {
            total += menuItem.getPrice();
        }
        Cheque cheque = new Cheque();

        cheque.setPriceTotal(total);
        cheque.setService(total * cheque.procient);
        cheque.setUser(currentUser);
        cheque.getMenuItems().addAll(allMenuItems);
        chequeRepository.save(cheque);


        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Успешно сохранен чек !")
                .build();
    }

    @Override
    @Transactional
    public DefaultResponse update(Long cheId, MenuItemCheckRequest checkRequest) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepository.getByEmail(email);
        if (!current.getRole().equals(Role.ADMIN)){
            throw new AccessDeniedException("Forbidden !");
        }

        Cheque cheque = chequeRepository.getByIds(cheId);

        List<Long> menu = new ArrayList<>();
        menu.addAll(checkRequest.menuItemIds());
        List<MenuItem> updateMenuId = menuItemRepository.getAllMenuId(menu);

        if (updateMenuId.isEmpty()) {
            throw new NotFoundException("Error !");
        } else {

            cheque.getMenuItems().addAll(updateMenuId);
            int total = 0;
            for (MenuItem menuItem : cheque.getMenuItems()) {
                total += menuItem.getPrice();
            }
            double service = total * (cheque.procient);
            cheque.setPriceTotal(total);
            cheque.setService(service);
            return DefaultResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(" Успешно изменен чек !")
                    .build();
        }
    }

    @Override
    public DefaultResponse delete(Long cheId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepository.getByEmail(email);
        if (!current.getRole().equals(Role.ADMIN)){
            throw new AccessDeniedException("Forbidden !");
        }

        chequeRepository.deleteMenu(cheId);
        chequeRepository.delete(chequeRepository.getByIds(cheId));
        return DefaultResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Успешно удален чек !")
                .build();
    }

    @Override
    public List<GetCheckResponse> getAll() {
        getCurrentUser();
        List<GetCheckResponse> getAll = new ArrayList<>();
        List<Cheque> getCheckResponses = chequeRepository.findAll();
        for (Cheque getCheckRespons : getCheckResponses) {
           getAll.add(findCheckById(getCheckRespons.getId()));
        }
        return getAll;
    }

    @Override
    public GetCheckResponse findCheckById(Long checkId) {

        getCurrentUser();
        chequeRepository.getByIds(checkId);
        ChequeResponse find = chequeRepository.findBY(checkId);
        List<MenuResponse> menuItemResponse = menuItemRepository.checkByIds(checkId);

        double grandTotal = find.priceTotal() + find.service();

        return GetCheckResponse.builder()
                .chequeResponse(find)
                .menuItems(menuItemResponse)
                .percent(find.percent()+"%")
                .grandTotal(grandTotal)
                .build();
    }

    @Override
    public SumCheckResponse sumWaiter() {
        User currentUser = getCurrentUser();
        List<Cheque> cheques = chequeRepository.getAllSum(currentUser);

        double total = 0;
        double totalPrice = 0;
        double totalService = 0;
        double totalProcient = 0;

        for (Cheque cheque : cheques) {
            if (cheque.getCreatedAt().isAfter(ZonedDateTime.now().minusHours(24))) {
                if (currentUser.getId().equals(cheque.getUser().getId())) {
                    totalPrice += cheque.getPriceTotal();
                    totalService += cheque.getService();
                    totalProcient = cheque.getProcient();
                }
            }
        }

        total = totalPrice + totalService;

        SumCheckResponse sumCheckResponse = new SumCheckResponse();
        sumCheckResponse.setService(String.valueOf(totalService));
        sumCheckResponse.setPriceTotal(String.valueOf(totalPrice));
        sumCheckResponse.setProcient(totalProcient);
        sumCheckResponse.setFirstName(currentUser.getFirstName());
        sumCheckResponse.setRole(currentUser.getRole());
        sumCheckResponse.setFullTotal(String.valueOf(total));

        return sumCheckResponse;
    }

    @Override
    public ResSumResponse getAverage(Long resId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepository.getByEmail(email);
        if (!current.getRole().equals(Role.ADMIN)){
            throw new AccessDeniedException("Forbidden !");
        }

        Restaurant restaurant = restaurantRepository.getByID(resId);
        List<Cheque> cheques =  chequeRepository.getAllSumRest(restaurant.getId());
        ResSumResponse response = new ResSumResponse();


        double totalAverage = 0;
        double priceTotals = 0;
        double service = 0;
        for (Cheque cheque : cheques) {
            if (cheque.getCreatedAt().isAfter(ZonedDateTime.now().minusHours(24))) {
                totalAverage += (cheque.getPriceTotal() + cheque.getService());
                priceTotals += cheque.getPriceTotal();
                service += cheque.getService();

            }
        }

            response.setPriceTotal(String.valueOf(priceTotals));
            response.setService(String.valueOf(service));

        response.setName(restaurant.getName());
        response.setAverageTotal(String.valueOf(totalAverage));

        return response;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User current = userRepository.getByEmail(email);
        if (current.getRole().equals(Role.ADMIN) || current.getRole().equals(Role.WAITER))
            return current;
        else throw new ForbiddenException("Forbidden 403");
    }
}
