package vroong.laas.dispatch.core.application.dispatch;

import lombok.RequiredArgsConstructor;
import vroong.laas.dispatch.core.common.annotation.Facade;
import vroong.laas.dispatch.core.domain.dispatch.AgentProposal;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatch;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.service.DispatchProposalService;
import vroong.laas.dispatch.core.domain.dispatch.service.DispatchRequestService;

@Facade
@RequiredArgsConstructor
public class DispatchFacade {

  private final DispatchRequestService dispatchRequestService;
  private final DispatchProposalService dispatchProposalService;

  public Long request(NewDispatch newDispatch) {
    // todo: 진행 중인 배차 확인
    return dispatchRequestService.request(newDispatch);
  }

  public void propose(AgentProposal agentProposal) {
    dispatchProposalService.propose(agentProposal);
  }



}
