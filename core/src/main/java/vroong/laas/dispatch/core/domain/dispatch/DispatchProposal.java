package vroong.laas.dispatch.core.domain.dispatch;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;

@Getter
public class DispatchProposal {

  private Long id;
  private Long dispatchId;
  private Long orderId;
  private Long agentId;
  private BigDecimal suggestedFee;
  private DispatchProposalStatus status;
  private Instant proposedAt;
  private Instant expiresAt;
  private Instant respondedAt;

  public DispatchProposal(
      Long id,
      Long dispatchId,
      Long orderId,
      Long agentId,
      BigDecimal suggestedFee,
      DispatchProposalStatus status,
      Instant proposedAt,
      Instant expiresAt,
      Instant respondedAt) {
    this.id = id;
    this.dispatchId = dispatchId;
    this.orderId = orderId;
    this.agentId = agentId;
    this.suggestedFee = suggestedFee;
    this.status = status;
    this.proposedAt = proposedAt;
    this.expiresAt = expiresAt;
    this.respondedAt = respondedAt;
  }

  public void accept() {
    if (this.status != DispatchProposalStatus.PROPOSED) {
      throw new IllegalStateException("배차 진행중인 제안만 수락할 수 있습니다");
    }

    this.status = DispatchProposalStatus.ACCEPTED;
    this.respondedAt = Instant.now();
  }

  public void decline() {
    this.status = DispatchProposalStatus.DECLINED;
    this.respondedAt = Instant.now();
  }
}
