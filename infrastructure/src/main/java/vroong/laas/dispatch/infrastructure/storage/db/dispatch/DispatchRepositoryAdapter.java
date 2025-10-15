package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequestStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryAdapter implements DispatchRepository {

  private final DispatchRequestJpaRepository dispatchRequestJpaRepository;

  @Override
  public DispatchRequest storeDispatchRequest(NewDispatchRequest newDispatchRequest) {
    DispatchRequestEntity dispatchRequestEntity = DispatchRequestEntity.builder()
        .orderId(newDispatchRequest.orderId())
        .status(newDispatchRequest.status().name())
        .assignedAgentId(null)
        .dispatchedAt(null)
        .cancelledAt(null)
        .build();

    DispatchRequestEntity savedDispatchRequestEntity =
        dispatchRequestJpaRepository.save(dispatchRequestEntity);

    return new DispatchRequest(
        savedDispatchRequestEntity.getId(),
        savedDispatchRequestEntity.getOrderId(),
        DispatchRequestStatus.valueOf(savedDispatchRequestEntity.getStatus()),
        savedDispatchRequestEntity.getAssignedAgentId(),
        savedDispatchRequestEntity.getDispatchedAt(),
        savedDispatchRequestEntity.getCancelledAt());
  }
}
