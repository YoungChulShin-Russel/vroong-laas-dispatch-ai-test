package vroong.laas.dispatch.core.domain.dispatch;

import java.time.Instant;
import lombok.Getter;
import vroong.laas.dispatch.core.domain.shared.AggregateRoot;

@Getter
public class DispatchRequest extends AggregateRoot {

  private Long id;
  private Long orderId;
  private DispatchRequestStatus status;
  private Long agentId;
  private Instant dispatchedAt;
  private Instant cancelledAt;

  public DispatchRequest(
      Long id,
      Long orderId,
      DispatchRequestStatus status,
      Long agentId,
      Instant dispatchedAt,
      Instant cancelledAt) {
    if (id == null) {
      throw new IllegalArgumentException("ID는 필수입니다");
    }
    if (orderId == null) {
      throw new IllegalArgumentException("오더 아이디는 필수입니다");
    }
    if (status == null) {
      throw new IllegalArgumentException("배차 요청 상태는 필수입니다");
    }

    this.id = id;
    this.orderId = orderId;
    this.status = status;
    this.agentId = agentId;
    this.dispatchedAt = dispatchedAt;
    this.cancelledAt = cancelledAt;
  }
}
