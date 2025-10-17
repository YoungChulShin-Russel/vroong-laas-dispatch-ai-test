package vroong.laas.dispatch.core.domain.dispatch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatch;
import vroong.laas.dispatch.core.domain.dispatch.required.DispatchRepository;

@Service
@RequiredArgsConstructor
public class DispatchRequestService {

  private final DispatchRepository dispatchRepository;

  @Transactional
  public Long request(NewDispatch newDispatch) {
    Dispatch dispatch = dispatchRepository.saveDispatch(newDispatch);

    // todo: 이벤트 발행
    return dispatch.getId();
  }
}
