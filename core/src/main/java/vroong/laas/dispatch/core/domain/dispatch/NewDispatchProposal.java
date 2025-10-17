package vroong.laas.dispatch.core.domain.dispatch;

import java.math.BigDecimal;
import java.time.Instant;

public record NewDispatchProposal(
    Long orderId,
    DispatchProposalStatus status,
    Long agentId,
    BigDecimal suggestedFee,
    Instant proposedAt,
    Instant expiresAt
) {
}
