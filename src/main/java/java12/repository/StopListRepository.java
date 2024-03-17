package java12.repository;

import java12.dto.response.StopResponse;
import java12.entity.MenuItem;
import java12.entity.StopList;
import java12.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface StopListRepository extends JpaRepository<StopList, Long> {
    boolean existsByMenuItemAndDate(MenuItem menu, LocalDateTime date);

    default StopList getByIds(Long stopId) {
        return findById(stopId).orElseThrow(() -> new NotFoundException(" Нет такой стоп-лист id  " + stopId));
    }


    @Query("select new java12.dto.response.StopResponse(m.name, s.reason, s.date) from StopList s join s.menuItem m ")
    List<StopResponse> getAll();

    @Query("select new java12.dto.response.StopResponse(m.name, s.reason, s.date) from StopList s join s.menuItem m where s.id=:stopId")
    StopResponse getStopId(Long stopId);

//    boolean existsByMenuItemId(MenuItem menuItem);

    @Query("select s from StopList s join s.menuItem mi where mi.id = :menuId")
    StopList existsByMenuItemIds(Long menuId);


    @Query("select s from StopList s join s.menuItem m where s.id =:id")
    StopList getByMenuItemId(Long id);
}