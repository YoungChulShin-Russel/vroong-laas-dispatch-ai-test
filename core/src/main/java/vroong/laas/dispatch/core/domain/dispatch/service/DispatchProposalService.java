package vroong.laas.dispatch.core.domain.dispatch.service;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.AgentProposal;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposalStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchProposalService {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public void propose(AgentProposal agentProposal) {
    NewDispatchProposal newDispatchProposal = new NewDispatchProposal(
        agentProposal.orderId(),
        DispatchProposalStatus.PROPOSED,
        agentProposal.agentId(),
        agentProposal.suggestedFee(),
        Instant.now(),
        Instant.now().plusSeconds(30));

    DispatchProposal dispatchProposal =
        dispatchRepository.saveDispatchProposal(newDispatchProposal);
  }
}
