package java12.api;

import java12.dto.request.Subcategoryrequest;
import java12.dto.response.*;
import java12.service.CategoryService;
import java12.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub")
public class SubcategoryAPI {
    private final SubcategoryService subcategoryService;

    @Secured({"ADMIN","CHEF"})
    @PostMapping("/{catId}")
    public DefaultResponse save(@PathVariable Long catId,@RequestBody Subcategoryrequest subcategoryrequest){
        return subcategoryService.save(catId,subcategoryrequest);
    }
    @Secured({"ADMIN","CHEF"})
    @GetMapping("/{subId}")
    public DefaultResponse update(@PathVariable Long subId,@RequestBody Subcategoryrequest subcategoryrequest){
        return subcategoryService.update(subId,subcategoryrequest);
    }
    @Secured({"ADMIN","CHEF"})

    @GetMapping("/find/{subId}")
    public Subcategoryresponse findById(@PathVariable Long subId){
        return subcategoryService.findById(subId);
    }
    @Secured({"ADMIN","CHEF"})

    @GetMapping("/all/{catId}")
    public List<SubcategoryAll> findAll(@RequestParam int page, @RequestParam int size ,@PathVariable Long catId){
        return subcategoryService.findAll(page,size,catId);
    }
    @Secured({"ADMIN"})
    @PostMapping("/delete/{subId}")
    public DefaultResponse delete(@PathVariable Long subId){
        return subcategoryService.delete(subId);
    }

    @Secured({"ADMIN","CHEF"})
    @GetMapping("/all")
    public List<SubcategoryCategoryResponse> allCategory(){
        return subcategoryService.getAllCategory();
    }


    @Secured({"ADMIN","CHEF"})
    @GetMapping("/search")
    public List <SubcategoryCategoryResponse> search(@RequestParam String word){
        return subcategoryService.search(word);
    }

}
