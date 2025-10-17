package vroong.laas.dispatch.core.domain.dispatch;

import java.math.BigDecimal;

public record AgentProposal(
    Long orderId,
    Long agentId,
    BigDecimal suggestedFee
) {

}
