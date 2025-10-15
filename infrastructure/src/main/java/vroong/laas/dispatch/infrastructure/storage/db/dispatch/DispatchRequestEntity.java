package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vroong.laas.dispatch.infrastructure.storage.db.ConcurrentEntity;

@Entity
@Table(name = "dispatch_requests")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DispatchRequestEntity extends ConcurrentEntity {

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "assigned_agent_id")
    private Long assignedAgentId;

    @Column(name = "dispatched_at")
    private Instant dispatchedAt;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    @Builder
    public DispatchRequestEntity(
            Long orderId,
            String status,
            Long assignedAgentId,
            Instant dispatchedAt,
            Instant cancelledAt) {
        this.orderId = orderId;
        this.status = status;
        this.assignedAgentId = assignedAgentId;
        this.dispatchedAt = dispatchedAt;
        this.cancelledAt = cancelledAt;
    }
}