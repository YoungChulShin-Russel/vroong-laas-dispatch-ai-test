package vroong.laas.dispatch.api.web.dispatch.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProposeDispatchRequest(
    @NotNull Long orderId,
    @NotNull Long agentId,
    @NotNull @Min(0) BigDecimal amount
) {

}
