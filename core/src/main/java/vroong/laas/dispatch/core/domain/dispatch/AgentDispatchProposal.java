package vroong.laas.dispatch.core.domain.dispatch;

import java.math.BigDecimal;

public record AgentDispatchProposal(
    Long agentId,
    Long orderId,
    BigDecimal suggestedFee
) {

}
