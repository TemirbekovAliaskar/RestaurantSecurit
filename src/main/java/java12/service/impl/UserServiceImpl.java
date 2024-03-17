package java12.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java12.dto.request.SignInRequest;
import java12.dto.request.UpdateUserRequest;
import java12.dto.request.UserRequestChef;
import java12.dto.request.UserRequestWaiter;
import java12.dto.response.FindUserResponse;
import java12.dto.response.GetAllUserResponse;
import java12.dto.response.SimpleResponse;
import java12.dto.response.UserResponse;
import java12.entity.Restaurant;
import java12.entity.User;
import java12.entity.enums.Role;
import java12.exception.FilledException;
import java12.exception.NotFoundException;
import java12.exception.response.ExceptionResponse;
import java12.repository.RestaurantRepository;
import java12.repository.UserRepository;
import java12.security.jwt.JwtService;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostConstruct
    public  void  saveAdmin(){

        String encode  = passwordEncoder.encode("urmat12");
        User admin = User
                .builder()
                .firstName("Urmat")
                .lastName("Taichikov")
                .dateOfBirth(ZonedDateTime.now().toLocalDate())
                .email("urmat@gmail.com")
                .password(encode)
                .phoneNumber("+996555750042")
                .experience(3)
                .role(Role.ADMIN)
                .build();

//        log.info("before save user");
        User user = userRepository.save(admin);
        log.info("user saved");

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Bahchai");
        restaurant.setLocation("Vostok 5");
        restaurant.setRestType("EURO");
        restaurant.setServicePercent(15);

        restaurantRepository.save(restaurant);
        restaurant.getUsers().add(user);
        restaurant.setNumberOfEmployees(restaurant.getUsers().size());
        restaurantRepository.save(restaurant);



    }

    @Override
    public SimpleResponse save(UserRequestChef userRequestChef) {
           boolean exist = userRepository.existsByEmail(userRequestChef.email());
            if (exist) throw new NotFoundException("Email : " + userRequestChef.email() + "уже существует!");
            User user = new User();
            user.setFirstName(userRequestChef.firstName());
            user.setLastName(userRequestChef.lastName());
            user.setDateOfBirth(userRequestChef.dateOfBirth());
            user.setEmail(userRequestChef.email());
            user.setPassword(passwordEncoder.encode(userRequestChef.password()));
            user.setPhoneNumber(userRequestChef.phoneNumber());
            user.setRole(userRequestChef.role());
            user.setExperience(userRequestChef.experience());
            userRepository.save(user);
            log.info(" Пользователь регистрировался CHEF !");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully !")
                .token(jwtService.createToken(user))
                .build();
    }

    @Override @Transactional
    public UserResponse asSign(Long restaurantId, Long userId) {

        Restaurant restaurant = restaurantRepository.getByID(restaurantId);
        User user = userRepository.getByIdUser(userId);
        if (restaurant.getUsers().size()>=15){
            return UserResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(" Сотрудников хватает !!!")
                    .build();
        }
            restaurant.getUsers().add(user);
            restaurant.setNumberOfEmployees(restaurant.getUsers().size());


        return UserResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(" Поздравляю вы уже сотрудник ресторана !")
                .build();
    }

    @Override
    public SimpleResponse signIn(SignInRequest sign) {
        User user = userRepository.getByEmail(sign.email());
        boolean matches = passwordEncoder.matches(sign.password(),user.getPassword());
        log.info(" User sign in");
        if (!matches) throw new NotFoundException("Пароль не корректный  !");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Удачно поступили на на работу вы приняты !")
                .token(jwtService.createToken(user))
                .build();
    }

    @Override @Transactional
    public UserResponse update(Long userId, UpdateUserRequest userRequest) {
        User user = userRepository.getByIdUser(userId);
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        return UserResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated !")
                .build();
    }

    @Override
    public SimpleResponse saveWaiter(UserRequestWaiter userRequestWaiter) {
        boolean exist = userRepository.existsByEmail(userRequestWaiter.email());
        if (exist) throw new NotFoundException("Email : " + userRequestWaiter.email() + " уже существует!");
        User user = new User();
        user.setFirstName(userRequestWaiter.firstName());
        user.setLastName(userRequestWaiter.lastName());
        user.setDateOfBirth(userRequestWaiter.dateOfBirth());
        user.setEmail(userRequestWaiter.email());
        user.setPassword(passwordEncoder.encode(userRequestWaiter.password()));
        user.setPhoneNumber(userRequestWaiter.phoneNumber());
        user.setRole(userRequestWaiter.role());
        user.setExperience(userRequestWaiter.experience());
        userRepository.save(user);
        log.info(" Пользователь регистрировался WAITER !");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully !")
                .token(jwtService.createToken(user))
                .build();
    }

    @Override
    public UserResponse delete(Long userId) {
        User user = userRepository.getByIdUser(userId);
        restaurantRepository.deleteByUserId(userId);
        userRepository.delete(user);
        return UserResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Удачно удалено  !")
                .build();
    }

    @Override
    public List<GetAllUserResponse> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> userPage = userRepository.getAllPage(pageable);

        List<GetAllUserResponse> responses = new ArrayList<>();
        for (User user : userPage.getContent()) {
            GetAllUserResponse response = GetAllUserResponse.builder()
                    .page(userPage.getNumber() + 1)
                    .size(userPage.getTotalPages())
                    .users(Collections.singletonList(user))
                    .build();
            responses.add(response);
        }
        return responses;
    }
}
