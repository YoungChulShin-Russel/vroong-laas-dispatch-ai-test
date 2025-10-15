package vroong.laas.dispatch.infrastructure.outbox;

import com.vroong.msa.kafka.event.KafkaEvent;
import com.vroong.msa.kafka.event.KafkaEventPayload;
import com.vroong.msa.kafka.event.KafkaEventSource;
import com.vroong.msa.kafka.event.KafkaEventType;
import com.vroong.msa.kafka.event.payload.order.OrderCreatedKafkaEventPayload;
import java.util.List;
import vroong.laas.dispatch.core.domain.outbox.OutboxEventType;
import vroong.laas.dispatch.core.domain.shared.AggregateRoot;

/**
 * Kafka Outbox Event Mapper
 *
 * <p>Domain Model을 Kafka Event Payload로 변환합니다.
 *
 * <p>책임:
 * - OutboxEventType에 따라 적절한 매핑 메서드 호출
 * - Domain Model(Order)을 OrderCreatedKafkaEventPayload로 변환
 * - KafkaEvent 생성 (eventType, eventSource, payload)
 *
 * <p>변환 과정:
 * 1. OrderItem → OrderCreatedOrderItem
 * 2. Origin/Destination → OrderCreatedOrderLocation
 * 3. DeliveryPolicy → OrderCreatedOrderDeliveryPolicy
 * 4. Order → OrderCreatedKafkaEventPayload
 * 5. KafkaEvent 생성
 * 6. KafkaOutboxEvent 반환 (eventKey + kafkaEvent)
 */
class KafkaOutboxEventMapper {

  private final KafkaEventSource ORDER_EVENT_SOURCE = KafkaEventSource.ORDER;

  public KafkaOutboxEvent map(OutboxEventType eventType, AggregateRoot aggregateRoot) {
    throw new IllegalArgumentException("지원하지 않는 이벤트 타입입니다: " + eventType);
  }

//  private KafkaOutboxEvent mapToOrderCreatedEvent(Order order) {
//    return new KafkaOutboxEvent(String.valueOf(order.getId()), kafkaEvent);
//  }
}
