package vroong.laas.dispatch.eventconsumer.kafka;

import com.vroong.msa.kafka.event.KafkaEvent;
import com.vroong.msa.kafka.event.KafkaEventPayload;
import com.vroong.msa.kafka.event.KafkaEventType;
import com.vroong.msa.kafka.event.payload.order.OrderCreatedKafkaEventPayload;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vroong.laas.dispatch.core.application.dispatch.DispatchFacade;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

  private final DispatchFacade dispatchFacade;

  // todo: topic 가져오는 부분 코드화
  @KafkaListener(
      topics = "order_event",
      groupId = "dispatch-event-consumer",
      errorHandler = "kafkaErrorHandler",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void handleOrderEvent(@Payload String payloadJson, Acknowledgment ack) {
    log.info("handleOrderEvent start, payload={}", payloadJson);

    // todo: 받았을 때 처리하면 응답이 밀릴 수 있기 때문에, 받은 정보를 저장하고, 별도로 처리하는게 필요
    KafkaEvent<KafkaEventPayload> kafkaEvent = KafkaEvent.fromJson(payloadJson);
    if (kafkaEvent.getType() == KafkaEventType.ORDER_ORDER_CREATED) {
      OrderCreatedKafkaEventPayload payload = (OrderCreatedKafkaEventPayload) kafkaEvent.getPayload();
      dispatchFacade.requestDispatch(payload.getOrderId());
    }

    ack.acknowledge();

    log.info("handleOrderEvent end,");
  }

}
