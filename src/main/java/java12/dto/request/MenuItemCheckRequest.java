package java12.dto.request;

import java.util.List;

public record MenuItemCheckRequest(List<Long> menuItemIds) {
}
