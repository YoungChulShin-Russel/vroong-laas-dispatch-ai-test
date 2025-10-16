package vroong.laas.dispatch.infrastructure.storage.db.dispatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRequestJpaRepository extends JpaRepository<DispatchEntity, Long> {

}
