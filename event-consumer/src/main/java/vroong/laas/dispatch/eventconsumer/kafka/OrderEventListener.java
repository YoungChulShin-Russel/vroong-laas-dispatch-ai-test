package vroong.laas.dispatch.eventconsumer.kafka;

import com.vroong.msa.kafka.event.KafkaEventPayload;
import com.vroong.msa.kafka.event.payload.order.OrderCreatedKafkaEventPayload;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import vroong.laas.dispatch.core.application.dispatch.DispatchFacade;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

  private final DispatchFacade dispatchFacade;

  // todo: topic 가져오는 부분 코드화
  // todo: containerFactory 직접 구현
  @KafkaListener(
      topics = "order.order.event",
      groupId = "dispatch-event-consumer",
      errorHandler = "kafkaErrorHandler",
      containerFactory = "kafkaListenerContainerFactory"
  )
  public void handleOrderEvent(
      @Payload OrderCreatedKafkaEventPayload payload,
      @Header Map<String, Object> headers,
      Acknowledgment ack) {
    log.info("handleOrderEvent start, payload={}, headers={}", payload, headers);

    dispatchFacade.requestDispatch(payload.getOrderId());
    ack.acknowledge();

    log.info("handleOrderEvent end,");
  }

}
