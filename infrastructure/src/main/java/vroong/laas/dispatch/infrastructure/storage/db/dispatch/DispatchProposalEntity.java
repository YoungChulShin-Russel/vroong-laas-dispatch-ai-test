package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposal;
import vroong.laas.dispatch.core.domain.dispatch.DispatchProposalStatus;
import vroong.laas.dispatch.infrastructure.storage.db.BaseEntity;

@Entity
@Table(name = "dispatch_proposals")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DispatchProposalEntity extends BaseEntity {

    @Column(name = "dispatch_id", nullable = false)
    private Long dispatchId;

    @Column(name = "agent_id", nullable = false)
    private Long agentId;

    @Column(name = "suggested_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal suggestedFee;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DispatchProposalStatus status;

    @Column(name = "proposed_at", nullable = false)
    private Instant proposedAt;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "responded_at")
    private Instant respondedAt;

    @Builder
    public DispatchProposalEntity(
        Long dispatchId,
        Long agentId,
        BigDecimal suggestedFee,
        DispatchProposalStatus status,
        Instant proposedAt,
        Instant expiresAt,
        Instant respondedAt) {
        this.dispatchId = dispatchId;
        this.agentId = agentId;
        this.suggestedFee = suggestedFee;
        this.status = status;
        this.proposedAt = proposedAt;
        this.expiresAt = expiresAt;
        this.respondedAt = respondedAt;
    }

    public DispatchProposal toDomain(Long orderId) {
        return new DispatchProposal(
            this.getId(),
            this.dispatchId,
            orderId,
            this.agentId,
            this.suggestedFee,
            this.status,
            this.proposedAt,
            this.expiresAt,
            this.respondedAt);
    }

    public void updateFromDomain(DispatchProposal dispatchProposal) {
        this.dispatchId = dispatchProposal.getDispatchId();
        this.agentId = dispatchProposal.getAgentId();
        this.suggestedFee = dispatchProposal.getSuggestedFee();
        this.status = dispatchProposal.getStatus();
        this.proposedAt = dispatchProposal.getProposedAt();
        this.expiresAt = dispatchProposal.getExpiresAt();
        this.respondedAt = dispatchProposal.getRespondedAt();
    }


}