package java12.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java12.dto.request.SignInRequest;
import java12.dto.request.UpdateUserRequest;
import java12.dto.request.UserRequestChef;
import java12.dto.request.UserRequestWaiter;
import java12.dto.response.FindUserResponse;
import java12.dto.response.GetAllUserResponse;
import java12.dto.response.SimpleResponse;
import java12.dto.response.UserResponse;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Auth API")
public class UserAPI {
    private final UserService userService;


    @GetMapping
    public SimpleResponse signIn(@RequestBody SignInRequest sign){
       return userService.signIn(sign);
    }

    @Secured("ADMIN")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid UserRequestChef userRequestChef){
        return userService.save(userRequestChef);
    }

    @Secured("ADMIN")
    @PostMapping("/save/waiter")
    public SimpleResponse saveWaiter(@RequestBody @Valid UserRequestWaiter userRequestWaiter){
       return userService.saveWaiter(userRequestWaiter);
    }


    @Secured("ADMIN")
    @PutMapping("/{restaurantId}/{userId}")
    public UserResponse asSign(@PathVariable Long restaurantId, @PathVariable Long userId){
        return userService.asSign(restaurantId,userId);
    }

    @Secured("ADMIN")
    @PostMapping("/update/{userId}")
    public UserResponse update(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest userRequest){
        return userService.update(userId,userRequest);
    }

    @Secured("ADMIN")

    @PostMapping("/delete/{userId}")
    public UserResponse delete(@PathVariable Long userId){
        return userService.delete(userId);
    }

    @Secured("ADMIN")
    @GetMapping("/all")
    public List<GetAllUserResponse> getAll(@RequestParam  int page,
                                          @RequestParam int size){
        return userService.getAll(page,size);
    }




}
