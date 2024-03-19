package java12.repository;

import java12.dto.response.FindUserResponse;
import java12.dto.response.GetAllUserResponse;
import java12.dto.response.UserFindResponse;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.exception.NotFoundException;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default User getByEmail(String email){
        return findByEmail(email).orElseThrow(() ->
                new NoSuchElementException("User with email: "+email+" not found!"));
    }
    @Query("select u from User u where u.email =:email")
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    default User getByIdUser(Long userId){
        return findById(userId).orElseThrow(() -> new NotFoundException("Not found user id" + userId));
    }

@Query("select u from User u")
    List<User> getAll();
    @Query("SELECT u FROM User u")
    Page<User> getAllPage(Pageable pageable);

    @Query("select new java12.dto.response.UserFindResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u where u.id =:userId")
    UserFindResponse findByIds(Long userId);
}