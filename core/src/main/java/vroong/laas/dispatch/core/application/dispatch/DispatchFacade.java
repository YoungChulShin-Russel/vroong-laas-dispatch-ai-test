package vroong.laas.dispatch.core.application.dispatch;

import lombok.RequiredArgsConstructor;
import vroong.laas.dispatch.core.common.annotation.Facade;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchOrder;
import vroong.laas.dispatch.core.domain.dispatch.service.DispatchRequestService;

@Facade
@RequiredArgsConstructor
public class DispatchFacade {

  private final DispatchRequestService dispatchRequestService;

  public Long request(DispatchRequest dispatchRequest) {
    // todo: 진행 중인 배차 확인
    return dispatchRequestService.request(dispatchRequest);
  }

  public void propose() {

  }

}
