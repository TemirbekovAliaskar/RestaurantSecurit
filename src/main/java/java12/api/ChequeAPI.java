package java12.api;

import java12.dto.request.MenuItemCheckRequest;
import java12.dto.response.*;
import java12.service.ChequeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cheque")
public class ChequeAPI {

    private final ChequeService chequeService;
    @Secured({"ADMIN","WAITER"})
    @PostMapping()
    public DefaultResponse save(@RequestBody MenuItemCheckRequest checkrequest) {
        return chequeService.save(checkrequest);
     }
    @Secured({"ADMIN"})
    @PostMapping("/update/{cheId}")
    public DefaultResponse update(@PathVariable Long cheId,
                                  @RequestBody  MenuItemCheckRequest checkRequest) {
        return chequeService.update(cheId, checkRequest);
     }
    @Secured({"ADMIN"})
    @PostMapping("/delete/{cheId}")
    public DefaultResponse delete(@PathVariable Long cheId) {
        return chequeService.delete(cheId);
    }

    @Secured("ADMIN")
    @GetMapping("/all")
    public List<GetCheckResponse> all() {
        return chequeService.getAll();
    }
    @Secured({"ADMIN","WAITER"})
    @GetMapping("/findCheck/{cheId}")
    public GetCheckResponse findCheckById(@PathVariable Long cheId) {
        return chequeService.findCheckById(cheId);
    }

    @Secured("ADMIN")
    @GetMapping("/sum")
    public SumCheckResponse getSum(){
        return chequeService.sumWaiter();
    }

    @Secured("ADMIN")
    @GetMapping("/summa/{resId}")
    public ResSumResponse getAverage(@PathVariable Long resId){
        return chequeService.getAverage(resId);
    }



}