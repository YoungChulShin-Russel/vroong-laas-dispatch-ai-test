package vroong.laas.dispatch.core.domain.dispatch;

import java.time.Instant;

public record NewDispatch(
    Long orderId,
    DispatchStatus status,
    Instant requestedAt
) {

  public NewDispatch(Long orderId, Instant requestedAt) {
    this(orderId, DispatchStatus.REQUESTED, requestedAt);
  }
}
