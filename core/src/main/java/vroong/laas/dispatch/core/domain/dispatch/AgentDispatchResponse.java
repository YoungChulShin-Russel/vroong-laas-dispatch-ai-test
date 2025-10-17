package vroong.laas.dispatch.core.domain.dispatch;

public record AgentDispatchResponse(
    Long dispatchProposalId,
    AgentDispatchResponseType responseType
) {
}
