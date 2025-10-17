package vroong.laas.dispatch.core.domain.dispatch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchProposalService {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public void propose(NewDispatchProposal dispatchProposal) {
    dispatchRepository.saveDispatchProposal(dispatchProposal);
  }
}
