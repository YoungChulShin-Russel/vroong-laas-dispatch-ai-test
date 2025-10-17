package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.DispatchStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatch;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Repository
@RequiredArgsConstructor
public class DispatchRepositoryAdapter implements DispatchRepository {

  private final DispatchJpaRepository dispatchJpaRepository;
  private final DispatchProposalJpaRepository dispatchProposalJpaRepository;

  @Override
  public Dispatch saveDispatch(NewDispatch dispatch) {
    DispatchEntity dispatchEntity = DispatchEntity.builder()
        .orderId(dispatch.orderId())
        .status(dispatch.status())
        .requestedAt(dispatch.requestedAt())
        .agentId(null)
        .dispatchedAt(null)
        .cancelledAt(null)
        .build();

    DispatchEntity savedDispatchEntity =
        dispatchJpaRepository.save(dispatchEntity);

    return new Dispatch(
        savedDispatchEntity.getId(),
        savedDispatchEntity.getOrderId(),
        savedDispatchEntity.getStatus(),
        savedDispatchEntity.getAgentId(),
        savedDispatchEntity.getRequestedAt(),
        savedDispatchEntity.getDispatchedAt(),
        savedDispatchEntity.getCancelledAt());
  }

  @Override
  public DispatchProposal saveDispatchProposal(NewDispatchProposal dispatchProposal) {
    // 현재 배차 진행중인 Dispatch 확인
    DispatchEntity dispatchEntity = dispatchJpaRepository.findByOrderIdAndStatus(
            dispatchProposal.orderId(), DispatchStatus.REQUESTED)
        .orElseThrow(() -> new IllegalArgumentException("배차 진행 중인 오더가 아닙니다"));

    // DispatchProposal 생성 및 저장
    DispatchProposalEntity dispatchProposalEntity = DispatchProposalEntity.builder()
        .dispatchId(dispatchEntity.getId())
        .agentId(dispatchProposal.agentId())
        .suggestedFee(dispatchProposal.suggestedFee())
        .status(dispatchProposal.status())
        .proposedAt(dispatchProposal.proposedAt())
        .expiresAt(dispatchProposal.expiresAt())
        .build();
    dispatchProposalJpaRepository.save(dispatchProposalEntity);

    // 응답
    return new DispatchProposal(
        dispatchProposalEntity.getId(),
        dispatchProposalEntity.getDispatchId(),
        dispatchEntity.getOrderId(),
        dispatchProposalEntity.getAgentId(),
        dispatchProposalEntity.getSuggestedFee(),
        dispatchProposalEntity.getStatus(),
        dispatchProposalEntity.getProposedAt(),
        dispatchProposalEntity.getExpiresAt(),
        dispatchProposalEntity.getRespondedAt());
  }
}
