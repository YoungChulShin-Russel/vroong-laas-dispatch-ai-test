package vroong.laas.dispatch.core.domain.dispatch.required;

import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequestStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchOrder;

public interface DispatchRepository {

  DispatchRequest storeDispatchRequest(
      NewDispatchOrder newDispatchOrder,
      DispatchRequestStatus dispatchRequestStatus);
}
