package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.MenuRequest;
import java12.dto.response.DefaultResponse;
import java12.dto.response.MenuResponse;
import java12.dto.response.MenuSearchResponse;
import java12.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuItemAPI {

    private final MenuService menuService;

    @Secured({"ADMIN"})
    @PostMapping("/save/{restId}/{subId}")
    public DefaultResponse save(@PathVariable Long restId,@PathVariable Long subId, @RequestBody @Valid MenuRequest menuRequest){
        return menuService.save(restId,subId,menuRequest);
    }
    @Secured({"ADMIN"})
    @PostMapping("/update/{menuId}")
    public DefaultResponse update(@PathVariable Long menuId,@RequestBody @Valid MenuRequest menuRequest){
        return menuService.update(menuId,menuRequest);
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping ("/all")
    public List<MenuResponse> getAll(){
        return menuService.getAll();
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/find/{menuId}")
    public MenuResponse findById(@PathVariable Long menuId){
        return menuService.findById(menuId);
    }
    @Secured({"ADMIN"})
    @PostMapping("/delete/{menuId}")
    public DefaultResponse delete(@PathVariable Long menuId){
        return menuService.delete(menuId);
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/search")
    public List <MenuSearchResponse> search(@RequestParam String word){
        return menuService.search(word);
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/sort")
    public List <MenuSearchResponse> sort(@RequestParam String word){
        return menuService.sort(word);
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/filter")
    public List <MenuSearchResponse> sort(@RequestParam boolean words){
        return menuService.filter(words);
    }





}
