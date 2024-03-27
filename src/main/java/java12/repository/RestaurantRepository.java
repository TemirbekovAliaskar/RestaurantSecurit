package java12.repository;

import jakarta.transaction.Transactional;
import java12.entity.Restaurant;
import java12.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    default Restaurant getByID(Long restaurantId){
        return findById(restaurantId).orElseThrow(() -> new NotFoundException("Not found restaurant id "+restaurantId));
    }


    @Modifying
    @Transactional
    @Query(value = "delete from restaurants_users where restaurant_id =:resId", nativeQuery = true)
    void deleteUser(Long resId);


    @Modifying
    @Transactional
    @Query(value = "delete from restaurants_users where users_id = :userId", nativeQuery = true)
    void deleteByUserId(Long userId);

    @Query("select r from Restaurant r")
    Page<Restaurant> getAllPage(Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from MenuItem m where m.restaurant.id=:resId")
    void getByMenu(Long resId);

    @Modifying
    @Transactional
    @Query(value = "delete from cheques_menu_items where menu_items_id in (select id from menu_item where restaurant_id = :resId)", nativeQuery = true)
    void getByCheck(Long resId);
    @Modifying
    @Transactional
    @Query("delete from StopList s where s.menuItem.id =:resId")
    void getByStopList(Long resId);
}