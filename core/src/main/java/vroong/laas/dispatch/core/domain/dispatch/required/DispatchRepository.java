package vroong.laas.dispatch.core.domain.dispatch.required;

import java.util.Optional;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatch;
import vroong.laas.dispatch.core.domain.dispatch.NewDispatchProposal;

public interface DispatchRepository {

  Dispatch saveDispatch(NewDispatch dispatch);

  Dispatch updateDispatch(Dispatch dispatch);

  Optional<Dispatch> findDispatchById(long id);



  DispatchProposal saveDispatchProposal(NewDispatchProposal dispatchProposal);

  DispatchProposal updateDispatchProposal(DispatchProposal dispatchProposal);

  Optional<DispatchProposal> findDispatchProposalById(Long id);





}
