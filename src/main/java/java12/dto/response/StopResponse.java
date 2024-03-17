package java12.dto.response;

import java12.entity.MenuItem;

import java.time.LocalDateTime;
import java.util.Date;

public record StopResponse(String name,String reason, LocalDateTime date) {
}
