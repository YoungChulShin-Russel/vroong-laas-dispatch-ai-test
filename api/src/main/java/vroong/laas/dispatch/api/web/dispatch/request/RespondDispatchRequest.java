package vroong.laas.dispatch.api.web.dispatch.request;

import jakarta.validation.constraints.NotNull;
import vroong.laas.dispatch.core.domain.dispatch.AgentDispatchResponse;
import vroong.laas.dispatch.core.domain.dispatch.AgentDispatchResponseType;

public record RespondDispatchRequest(
    @NotNull Long proposalId,
    @NotNull DispatchResponseAction action
) {

  public enum DispatchResponseAction {
    ACCEPT,
    DECLINE
  }

  public AgentDispatchResponse toAgentDispatchResponse() {
    return new AgentDispatchResponse(
        proposalId,
        (action == DispatchResponseAction.ACCEPT)
            ? AgentDispatchResponseType.ACCEPT
            : AgentDispatchResponseType.DECLINE);
  }
}
