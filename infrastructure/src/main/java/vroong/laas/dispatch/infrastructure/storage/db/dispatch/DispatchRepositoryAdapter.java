package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import java.util.Optional;
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

    return dispatchJpaRepository.save(dispatchEntity).toDomain();
  }

  @Override
  public Dispatch updateDispatch(Dispatch dispatch) {
    // 기존 Entity 조회
    DispatchEntity entity = dispatchJpaRepository.findById(dispatch.getId())
        .orElseThrow(() -> new IllegalArgumentException("배차 정보를 찾을 수 없습니다."));
    
    // 도메인 모델의 값으로 Entity 업데이트
    entity.updateFromDomain(dispatch);
    
    // 업데이트된 Entity를 도메인 모델로 변환하여 반환
    return entity.toDomain();
  }

  @Override
  public Optional<Dispatch> findDispatchById(long id) {
    Optional<DispatchEntity> dispatchEntityOptional = dispatchJpaRepository.findById(id);
    if (dispatchEntityOptional.isEmpty()) {
      return Optional.empty();
    }

    DispatchEntity dispatchEntity = dispatchEntityOptional.get();

    return Optional.of(dispatchEntity.toDomain());
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
    return dispatchProposalEntity.toDomain(dispatchEntity.getOrderId());
  }

  @Override
  public DispatchProposal updateDispatchProposal(DispatchProposal dispatchProposal) {
    // 기존 Entity 조회
    DispatchProposalEntity dispatchProposalEntity =
        dispatchProposalJpaRepository.findById(dispatchProposal.getId())
            .orElseThrow(() -> new IllegalArgumentException("배차 제안 정보를 찾을 수 없습니다."));
    
    // 도메인 모델의 값으로 Entity 업데이트
    dispatchProposalEntity.updateFromDomain(dispatchProposal);
    
    // 업데이트된 Entity를 도메인 모델로 변환하여 반환
    DispatchEntity dispatchEntity = dispatchJpaRepository.findByIdAndStatus(
            dispatchProposalEntity.getDispatchId(), DispatchStatus.REQUESTED)
        .orElseThrow(() -> new IllegalArgumentException("배차 진행 중인 오더가 아닙니다"));

    return dispatchProposalEntity.toDomain(dispatchEntity.getOrderId());
  }

  @Override
  public Optional<DispatchProposal> findDispatchProposalById(Long dispatchProposalId) {
    Optional<DispatchProposalEntity> dispatchProposalEntityOptional =
        dispatchProposalJpaRepository.findById(dispatchProposalId);
    if (dispatchProposalEntityOptional.isEmpty()) {
      return Optional.empty();
    }
    DispatchProposalEntity dispatchProposalEntity = dispatchProposalEntityOptional.get();

    Optional<DispatchEntity> dispatchEntityOptional =
        dispatchJpaRepository.findById(dispatchProposalEntity.getDispatchId());
    if (dispatchEntityOptional.isEmpty()) {
      return Optional.empty();
    }
    DispatchEntity dispatchEntity = dispatchEntityOptional.get();

    return Optional.of(dispatchProposalEntity.toDomain(dispatchEntity.getOrderId()));
  }
}
