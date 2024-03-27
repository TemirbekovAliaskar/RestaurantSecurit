package java12.repository;

import jakarta.transaction.Transactional;
import java12.dto.response.ChequeResponse;
import java12.entity.Cheque;
import java12.entity.User;
import java12.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    default Cheque getByIds(Long cheId){
        return findById(cheId).orElseThrow(()-> new NotFoundException(" Нет такой чек id " + cheId));
    }

    @Query("select  new java12.dto.response.ChequeResponse(u.firstName,c.priceTotal,c.service,c.procient,c.createdAt) from Cheque c join c.menuItems m  join c.user u where c.id =:checkId")
    ChequeResponse findBY(Long checkId);

    @Modifying
    @Transactional
    @Query(value = "delete from cheques_menu_items where cheque_id =:cheId",nativeQuery = true)
    void deleteMenu(Long cheId);

    @Query("select c from Cheque c where c.user=:user")
   List<Cheque> getAllSum(User user);
    @Query("select c from Cheque c join c.menuItems m join m.restaurant r where r.id =:id")
    List<Cheque> getAllSumRest(Long id);


//
//    @Query("SELECT NEW java12.dto.response.ChequeResponse(u.firstName, m.name, m.price, c.priceAverage, r.servicePercent) FROM Cheque c JOIN c.user u JOIN c.menuItems m JOIN m.restaurant r")
//    List<ChequeResponse> getAll();
}