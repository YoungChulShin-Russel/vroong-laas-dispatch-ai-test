package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequestStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchOrder;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryAdapter implements DispatchRepository {

  private final DispatchRequestJpaRepository dispatchRequestJpaRepository;

  @Override
  public DispatchRequest storeDispatchRequest(
      NewDispatchOrder newDispatchOrder,
      DispatchRequestStatus dispatchRequestStatus
      ) {
    DispatchRequestEntity dispatchRequestEntity = DispatchRequestEntity.builder()
        .orderId(newDispatchOrder.orderId())
        .status(dispatchRequestStatus)
        .assignedAgentId(null)
        .dispatchedAt(null)
        .cancelledAt(null)
        .build();

    DispatchRequestEntity savedDispatchRequestEntity =
        dispatchRequestJpaRepository.save(dispatchRequestEntity);

    return new DispatchRequest(
        savedDispatchRequestEntity.getId(),
        savedDispatchRequestEntity.getOrderId(),
        savedDispatchRequestEntity.getStatus(),
        savedDispatchRequestEntity.getAssignedAgentId(),
        savedDispatchRequestEntity.getDispatchedAt(),
        savedDispatchRequestEntity.getCancelledAt());
  }
}
