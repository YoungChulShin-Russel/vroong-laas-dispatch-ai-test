package vroong.laas.dispatch.core.domain.dispatch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchStatus;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchRequestService {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public Long request(DispatchRequest dispatchRequest) {
    Dispatch dispatch = dispatchRepository.storeDispatch(dispatchRequest);

    // todo: 이벤트 발행
    return dispatch.getId();
  }
}
