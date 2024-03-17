package java12.api;

import java12.dto.request.StopRequest;
import java12.dto.response.DefaultResponse;
import java12.dto.response.StopResponse;
import java12.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.LongSummaryStatistics;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stop")
public class StopListAPI {
    private final StopService stopService;

    @Secured({"ADMIN","CHEF"})
    @PostMapping("/{menuId}")
    public DefaultResponse save(@PathVariable Long menuId, @RequestBody StopRequest stopRequest){
        return stopService.save(menuId,stopRequest);
    }
    @Secured({"ADMIN","CHEF"})
    @PostMapping("/update/{stopId}")
    public DefaultResponse update(@PathVariable Long stopId,@RequestBody StopRequest stopRequest){
        return stopService.update(stopId,stopRequest);
    }
    @Secured({"ADMIN","CHEF"})
    @PostMapping ("/delete/{stopId}")
    public DefaultResponse delete(@PathVariable Long stopId){
        return stopService.deleteBy(stopId);
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/all")
    public List<StopResponse> getAll(){
        return stopService.getALL();
    }
    @Secured({"ADMIN","CHEF","WAITER"})
    @GetMapping("/find/{stopId}")
    public StopResponse find(@PathVariable Long stopId){
        return stopService.findBy(stopId);
    }
}
