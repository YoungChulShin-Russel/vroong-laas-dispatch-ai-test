package vroong.laas.dispatch.api.web.dispatch;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vroong.laas.dispatch.api.web.dispatch.request.ProposeDispatchRequest;
import vroong.laas.dispatch.core.application.dispatch.DispatchFacade;

@RestController
@RequestMapping("/api/v1/dispatches")
public class DispatchController {

  private final DispatchFacade dispatchFacade;

  public DispatchController(DispatchFacade dispatchFacade) {
    this.dispatchFacade = dispatchFacade;
  }

  @PostMapping("/propose")
  public void propose(@RequestBody @Valid ProposeDispatchRequest request) {
    dispatchFacade.propose(request.toAgentProposal());
  }

}
