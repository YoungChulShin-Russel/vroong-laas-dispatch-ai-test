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

    @Column(name = "proposed_at", nullable = false)
    private Instant proposedAt;

    @Builder
    public DispatchProposalEntity(
            Long dispatchRequestId,
            Instant proposedAt) {
        this.dispatchRequestId = dispatchRequestId;
        this.proposedAt = proposedAt;
    }
}