package vroong.laas.dispatch.core.domain.dispatch;

import java.time.Instant;

public record DispatchRequest(
    Long orderId,
    DispatchStatus status,
    Instant requestedAt
) {

  public DispatchRequest(Long orderId, Instant requestedAt) {
    this(orderId, DispatchStatus.REQUESTED, requestedAt);
  }
}
