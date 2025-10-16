package vroong.laas.dispatch.core.domain.dispatch.required;

import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchRequest;
import vroong.laas.dispatch.core.domain.dispatch.DispatchStatus;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchOrder;

public interface DispatchRepository {

  Dispatch storeDispatch(DispatchRequest dispatchRequest);
}
