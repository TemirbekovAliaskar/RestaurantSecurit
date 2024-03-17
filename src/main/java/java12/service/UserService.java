package java12.service;


import java12.dto.request.SignInRequest;
import java12.dto.request.UpdateUserRequest;
import java12.dto.request.UserRequestChef;
import java12.dto.request.UserRequestWaiter;
import java12.dto.response.FindUserResponse;
import java12.dto.response.GetAllUserResponse;
import java12.dto.response.SimpleResponse;
import java12.dto.response.UserResponse;
import java12.repository.UserRepository;

import java.util.List;

public interface UserService {
    SimpleResponse save(UserRequestChef userRequestChef);

    UserResponse asSign(Long restaurantId, Long userId);

    SimpleResponse signIn(SignInRequest sign);

    UserResponse update(Long userId, UpdateUserRequest userRequest);

    SimpleResponse saveWaiter(UserRequestWaiter userRequestWaiter);

    UserResponse delete(Long userId);

    List<GetAllUserResponse> getAll(int page, int size);

}
