package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vroong.laas.dispatch.infrastructure.storage.db.BaseEntity;

@Entity
@Table(name = "agent_proposals")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AgentProposalEntity extends BaseEntity {

    @Column(name = "dispatch_proposal_id", nullable = false)
    private Long dispatchProposalId;

    @Column(name = "agent_id", nullable = false)
    private Long agentId;

    @Column(name = "suggested_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal suggestedPrice;

    @Column(name = "proposal_status", nullable = false)
    private String proposalStatus;

    @Column(name = "proposed_at", nullable = false)
    private Instant proposedAt;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "responded_at")
    private Instant respondedAt;

    @Builder
    public AgentProposalEntity(
            Long dispatchProposalId,
            Long agentId,
            BigDecimal suggestedPrice,
            String proposalStatus,
            Instant proposedAt,
            Instant expiresAt,
            Instant respondedAt) {
        this.dispatchProposalId = dispatchProposalId;
        this.agentId = agentId;
        this.suggestedPrice = suggestedPrice;
        this.proposalStatus = proposalStatus;
        this.proposedAt = proposedAt;
        this.expiresAt = expiresAt;
        this.respondedAt = respondedAt;
    }
}