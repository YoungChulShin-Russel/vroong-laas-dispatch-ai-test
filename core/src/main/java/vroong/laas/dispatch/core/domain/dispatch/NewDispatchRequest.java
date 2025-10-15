package vroong.laas.dispatch.core.domain.dispatch;

public record NewDispatchRequest(
    Long orderId,
    DispatchRequestStatus status
) {

  public NewDispatchRequest(Long orderId) {
    this(orderId, DispatchRequestStatus.PENDING);
  }
}
