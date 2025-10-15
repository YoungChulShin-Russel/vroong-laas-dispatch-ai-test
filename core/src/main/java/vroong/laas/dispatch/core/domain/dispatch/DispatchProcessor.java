package vroong.laas.dispatch.core.domain.dispatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchProcessor {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public DispatchRequest requestDispatch(Long orderId) {
    NewDispatchRequest newDispatchRequest = new NewDispatchRequest(orderId);
    DispatchRequest dispatchRequest = dispatchRepository.storeDispatchRequest(newDispatchRequest);

    // todo: 이벤트 발행
    return dispatchRequest;
  }
}
