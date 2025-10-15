package vroong.laas.dispatch.core.domain.dispatch.required;

import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequestStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchRequest;

public interface DispatchRepository {

  DispatchRequest storeDispatchRequest(NewDispatchRequest newDispatchRequest);
}
