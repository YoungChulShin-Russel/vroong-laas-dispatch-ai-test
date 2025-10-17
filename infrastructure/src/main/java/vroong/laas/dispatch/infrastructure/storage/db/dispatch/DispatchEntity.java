package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vroong.laas.dispatch.core.domain.dispatch.Dispatch;
import vroong.laas.dispatch.core.domain.dispatch.DispatchStatus;
import vroong.laas.dispatch.infrastructure.storage.db.ConcurrentEntity;

@Entity
@Table(name = "dispatches")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DispatchEntity extends ConcurrentEntity {

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DispatchStatus status;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "requested_at")
    private Instant requestedAt;

    @Column(name = "dispatched_at")
    private Instant dispatchedAt;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    @Builder
    public DispatchEntity(
            Long orderId,
            DispatchStatus status,
            Long agentId,
            Instant requestedAt,
            Instant dispatchedAt,
            Instant cancelledAt) {
        this.orderId = orderId;
        this.status = status;
        this.agentId = agentId;
        this.requestedAt = requestedAt;
        this.dispatchedAt = dispatchedAt;
        this.cancelledAt = cancelledAt;
    }

    public Dispatch toDomain() {
        return new Dispatch(
            this.getId(),
            this.orderId,
            this.status,
            this.agentId,
            this.requestedAt,
            this.dispatchedAt,
            this.cancelledAt);
    }

    public void updateFromDomain(Dispatch dispatch) {
        this.orderId = dispatch.getOrderId();
        this.status = dispatch.getStatus();
        this.agentId = dispatch.getAgentId();
        this.requestedAt = dispatch.getRequestedAt();
        this.dispatchedAt = dispatch.getDispatchedAt();
        this.cancelledAt = dispatch.getCancelledAt();
    }
}