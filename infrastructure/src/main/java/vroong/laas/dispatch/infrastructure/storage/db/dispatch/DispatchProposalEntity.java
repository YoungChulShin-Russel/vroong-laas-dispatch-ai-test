package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vroong.laas.dispatch.infrastructure.storage.db.BaseEntity;

@Entity
@Table(name = "dispatch_proposals")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DispatchProposalEntity extends BaseEntity {

    @Column(name = "dispatch_request_id", nullable = false)
    private Long dispatchRequestId;

    @Column(name = "proposal_status", nullable = false)
    private String proposalStatus;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Builder
    public DispatchProposalEntity(
            Long dispatchRequestId,
            String proposalStatus,
            Instant completedAt) {
        this.dispatchRequestId = dispatchRequestId;
        this.proposalStatus = proposalStatus;
        this.completedAt = completedAt;
    }
}