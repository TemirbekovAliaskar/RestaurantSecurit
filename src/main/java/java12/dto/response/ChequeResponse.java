package java12.dto.response;

import java12.entity.MenuItem;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ChequeResponse(
        String firstName,
        Integer priceTotal,
        Double service,
                             Double percent,
                             ZonedDateTime createdAt) {}


