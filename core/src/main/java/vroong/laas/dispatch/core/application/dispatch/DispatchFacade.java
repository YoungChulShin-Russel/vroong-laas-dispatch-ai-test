package vroong.laas.dispatch.core.application.dispatch;

import lombok.RequiredArgsConstructor;
import vroong.laas.dispatch.core.common.annotation.Facade;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProcessor;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;

@Facade
@RequiredArgsConstructor
public class DispatchFacade {

  private final DispatchProcessor dispatchProcessor;

  public DispatchRequest requestDispatch(Long orderId) {
    // todo: 진행 중 배차 확인

    DispatchRequest dispatchRequest = dispatchProcessor.requestDispatch(orderId);

    return dispatchRequest;
  }

}
