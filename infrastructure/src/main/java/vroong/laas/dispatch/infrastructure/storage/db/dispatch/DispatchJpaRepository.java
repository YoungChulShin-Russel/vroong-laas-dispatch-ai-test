package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vroong.laas.dispatch.core.domain.dispatch.DispatchStatus;

@Repository
public interface DispatchJpaRepository extends JpaRepository<DispatchEntity, Long> {

  Optional<DispatchEntity> findByOrderIdAndStatus(Long orderId, DispatchStatus status);

}
