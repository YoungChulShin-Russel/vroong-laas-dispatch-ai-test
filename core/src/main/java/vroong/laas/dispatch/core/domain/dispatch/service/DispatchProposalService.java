package vroong.laas.dispatch.core.domain.dispatch.service;

import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.AgentDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposalStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchProposalService {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public DispatchProposal propose(AgentDispatchProposal agentDispatchProposal) {
    NewDispatchProposal newDispatchProposal = new NewDispatchProposal(
        agentDispatchProposal.orderId(),
        DispatchProposalStatus.PROPOSED,
        agentDispatchProposal.agentId(),
        agentDispatchProposal.suggestedFee(),
        Instant.now(),
        Instant.now().plusSeconds(30));

    return dispatchRepository.saveDispatchProposal(newDispatchProposal);
  }

  @Transactional
  public void accept(Long dispatchProposalId) {
    // dispatch proposal 업데이트
    DispatchProposal dispatchProposal =
        dispatchRepository.findDispatchProposalById(dispatchProposalId)
                .orElseThrow(() -> new IllegalArgumentException("배차 제안 정보를 찾을 수 없습니다"));

    dispatchProposal.accept();
    dispatchRepository.updateDispatchProposal(dispatchProposal);

    // dispatch 업데이트
    Dispatch dispatch = dispatchRepository.findDispatchById(dispatchProposal.getDispatchId())
        .orElseThrow(() -> new IllegalArgumentException("배차 정보를 찾을 수 없습니다"));

    dispatch.dispatch(dispatchProposal.getAgentId());
    dispatchRepository.updateDispatch(dispatch);

    // outbox 이벤트 발행: to AI 배차
    // outbox 이벤트 발행: to Delivery
  }

  @Transactional
  public void decline(Long dispatchProposalId) {
    DispatchProposal dispatchProposal =
        dispatchRepository.findDispatchProposalById(dispatchProposalId)
            .orElseThrow(() -> new IllegalArgumentException("배차 제안 정보를 찾을 수 없습니다"));

    dispatchProposal.decline();

    dispatchRepository.updateDispatchProposal(dispatchProposal);

    // outbox 이벤트 발행: to AI 배차
  }
}
