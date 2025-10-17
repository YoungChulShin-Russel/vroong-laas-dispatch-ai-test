package vroong.laas.dispatch.core.domain.dispatch.required;

import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatch;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;

public interface DispatchRepository {

  Dispatch saveDispatch(NewDispatch dispatch);

  DispatchProposal saveDispatchProposal(NewDispatchProposal dispatchProposal);



}
