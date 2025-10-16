package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryAdapter implements DispatchRepository {

  private final DispatchRequestJpaRepository dispatchRequestJpaRepository;

  @Override
  public Dispatch storeDispatch(DispatchRequest dispatchRequest) {
    DispatchEntity dispatchEntity = DispatchEntity.builder()
        .orderId(dispatchRequest.orderId())
        .status(dispatchRequest.status())
        .requestedAt(dispatchRequest.requestedAt())
        .agentId(null)
        .dispatchedAt(null)
        .cancelledAt(null)
        .build();

    DispatchEntity savedDispatchEntity =
        dispatchRequestJpaRepository.save(dispatchEntity);

    return new Dispatch(
        savedDispatchEntity.getId(),
        savedDispatchEntity.getOrderId(),
        savedDispatchEntity.getStatus(),
        savedDispatchEntity.getAgentId(),
        savedDispatchEntity.getRequestedAt(),
        savedDispatchEntity.getDispatchedAt(),
        savedDispatchEntity.getCancelledAt());
  }
}
